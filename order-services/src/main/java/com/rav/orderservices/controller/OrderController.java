package com.rav.orderservices.controller;

import com.rav.orderservices.dto.OrderRequestDto;
import com.rav.orderservices.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name= "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto ){
        orderService.placeOrder(orderRequestDto);
        return "placed order success.";
    }

    public String fallbackMethod(OrderRequestDto orderRequestDto, RuntimeException runtimeException){
        return "Some error has occurred, Please try after some time";
    }
}
