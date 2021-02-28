package com.lxy.openplatform.commons.beans;

import com.lxy.openplatform.commons.APIRoutingType;

public class ApiRoutingMQBean {

    private String key;//当前发生变化的redis的key是什么

    private APIRoutingType type;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public APIRoutingType getType() {
        return type;
    }

    public void setType(APIRoutingType type) {
        this.type = type;
    }
}
