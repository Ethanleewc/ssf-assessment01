package com.iss.ssfassessment01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.iss.ssfassessment01.model.Contact;

@Service
public class PizzaService {

    private static final String CONTACT_ENTITY = "orderlist";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final Contact ctc){
        redisTemplate.opsForList()
            .leftPush(CONTACT_ENTITY, ctc.getOrderid());
        redisTemplate.opsForHash()
            .put( CONTACT_ENTITY+ "_Map", ctc.getOrderid(), ctc);
    }

    public Contact findById(final String cusId){
        Contact result= (Contact)redisTemplate.opsForHash()
                .get(CONTACT_ENTITY+ "_Map", 
                cusId);
        return result;
    }
    
}
