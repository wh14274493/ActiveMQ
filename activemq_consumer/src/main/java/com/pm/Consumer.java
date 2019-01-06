package com.pm;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pm.domain.User;

@Component
public class Consumer {

    @JmsListener(destination = "${queue}")
    public void receive(String msg) {
        System.out.println("监听器收到msg:" + msg);
        User user = JSONObject.parseObject(msg,User.class);
        System.out.println(user.getId()+"-------"+user.getName());
    }
}
