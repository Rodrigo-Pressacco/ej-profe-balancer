package com.example.clientconsumer.controller;

import com.example.clientconsumer.consumer.IConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientConsumer")
public class ConsumerController {

    private final IConsumer restConsumer;

    @Autowired
    public ConsumerController(IConsumer restConsumer) {
        this.restConsumer = restConsumer;
    }

    @GetMapping("/test")
    public Object getApi(){
        ResponseEntity<List<Object>> response = restConsumer.getApi();
        System.out.println("El puerto utilizado es: "+response.getHeaders().get("port"));
        return response.getBody();
    }

}
