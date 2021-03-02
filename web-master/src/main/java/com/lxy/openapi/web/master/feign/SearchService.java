package com.lxy.openapi.web.master.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "OPENAPI-SEARCH", fallback = SearchServiceFallback.class)
public interface SearchService {
    @RequestMapping(value = "/search/searchlog", method = RequestMethod.POST)
    List<Map> searchLog(@RequestParam("paras") String paras);

    @RequestMapping(value = "/search/searchcount", method = RequestMethod.POST)
    Long searchLogCount(@RequestParam("paras") String paras);
	
	@RequestMapping(value = "/search/createindex", method = RequestMethod.POST)
    public int createindex();

    @RequestMapping(value = "/search/avg", method = RequestMethod.POST)
    public Map<String, Integer> statApiCountAndAvg(@RequestParam("reveivceStartTime") String startTime, @RequestParam("reveivceStopTime") String endTime);

}
