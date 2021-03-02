package com.lxy.openapi.web.master.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Role {
    private Integer id;
    private String name;
    private String remark;
    private Integer status;

}
