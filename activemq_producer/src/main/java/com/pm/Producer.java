package com.pm;

import com.alibaba.fastjson.JSONObject;
import com.pm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
@EnableScheduling
public class Producer {

    @Autowired
    private JmsMessagingTemplate template;
    @Autowired
    private Queue queue;
    private int i = 0;

    @Scheduled(fixedDelay = 4000)
    public void send(){
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("name"+i++);
        String userJson = JSONObject.toJSONString(user);
        System.out.println("userJson: "+userJson);
        template.convertAndSend(queue,userJson);
    }
}
