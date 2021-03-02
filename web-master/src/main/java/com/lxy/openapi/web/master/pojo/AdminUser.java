package com.lxy.openapi.web.master.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AdminUser {
    private Integer id;
    private String password;
    private String email;
    private String realName;
    private Integer status;

}
