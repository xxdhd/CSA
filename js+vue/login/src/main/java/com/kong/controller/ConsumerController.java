package com.kong.controller;

import com.kong.mapper.ConsumerMapper;
import com.kong.pojo.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerMapper consumerMapper;

    @GetMapping("/userlist")
    public List<Consumer> queryConsumerList(){
        List<Consumer> consumerList = consumerMapper.queryConsumerList();
        for(Consumer consumer:consumerList){
            System.out.println(consumer);
        }
        return consumerList;
    }

    @GetMapping("/adduser")
    public String add(){
        consumerMapper.add(new Consumer(3,"王五","123456",1,"147","1@qq.com","我是傻逼"));
        return "add ok";
    }

    @GetMapping("/updateuser")
    public String update(){
        consumerMapper.update(new Consumer(3,"王五","123456",1,"147","1@qq.com","我是sb"));
        return "update ok";
    }

    @GetMapping("deleteuser")
    public String delete(){
        consumerMapper.delete(3);
        return "delete ok";
    }
}
