package com.example.clientconsumer.consumer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "clientservice")
@LoadBalancerClient(name = "clientservice", configuration=CustomLoadBalancerConfiguration.class)
public interface IConsumer {

    @GetMapping("/getClients")
    ResponseEntity<List<Object>> getApi();

}
