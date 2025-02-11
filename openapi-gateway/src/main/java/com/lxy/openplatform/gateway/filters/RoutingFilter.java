package com.lxy.openplatform.gateway.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.lxy.openplatform.commons.constans.SystemParams;
import com.lxy.openplatform.gateway.feign.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 当前过滤器的主要作用是根据用户传递的标识来获取到用户实际想要访问的服务的id和地址,然后再进行转发
 */
@Component
public class RoutingFilter extends ZuulFilter {
    @Autowired
    private CacheService cacheService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 当前过滤器是在我们请求的时候执行的,而我们所有的请求都是转到了缓存服务,我们应该在转发之前就将请求转到另外的服务,所以是前置过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //这个过滤器是在前面的各种校验过滤器成功之后才执行的,所以它理论上是最后一个前置过滤器,所以order稍微高一些
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //如何拿到请求对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //从请求中获取用户的标识数据
        //我们应该从用户的请求参数中获取这个数据,那么请求的参数名是什么,我们怎么知道,所以我们作为服务端就要定义规则,我们要求用户必须通过method参数来传递
        String method = request.getParameter("method");

        //从redis根据用户传递的标识获取路由信息
        try {

            //获取当前参数的路由映射关系map,这里面放的就是我们的标识对应的服务的id和地址等信息,根据这个信息我们可以拿到要访问的地址和服务id,然后进行服务的跳转
            Map<Object, Object> apiMappingInfo = cacheService.hGetAll(SystemParams.METHOD_REDIS_PRE + method);

            //参数非空校验
            if (apiMappingInfo != null && apiMappingInfo.size() > 0) {

                Object serviceId = apiMappingInfo.get("serviceId");//我们要访问的服务的id

                String insideApiUrl = (String) apiMappingInfo.get("insideApiUrl");//我们要访问的地址

                //通过RequestContext设置我们的请求服务id和地址即可
                //设置我们要访问的地址
                currentContext.put(FilterConstants.SERVICE_ID_KEY, serviceId);
                currentContext.put(FilterConstants.REQUEST_URI_KEY, insideApiUrl);

                return null;//返回值没有任何意义
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //说明路由失败,拦截请求,返回信息
        currentContext.setSendZuulResponse(false);

        // 创建response
        HttpServletResponse response = currentContext.getResponse();

        //response.setContentType("html/text;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        currentContext.setResponseBody("路由失败，当前参数："+method);

        return null;
    }
}
