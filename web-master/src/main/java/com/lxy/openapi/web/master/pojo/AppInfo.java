package com.lxy.openapi.web.master.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppInfo {
    private Integer id;
    private String corpName;
    private String appName;
    private String appKey;
    private String appSecret;
    private String redirectUrl;
    private Integer limit;
    private String description;
    private Integer cusId;
    private Integer state;
}
