package com.lxy.openapi.web.master.mq;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SendApiRoutingChangeStream {

    @Output("apiroutingchange")
    MessageChannel message_channel();
}
