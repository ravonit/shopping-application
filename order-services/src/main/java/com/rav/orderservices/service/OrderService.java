package com.rav.orderservices.service;

import com.rav.orderservices.assembler.OrderAssembler;
import com.rav.orderservices.dto.InventoryResponseDto;
import com.rav.orderservices.dto.OrderRequestDto;
import com.rav.orderservices.entity.LineItems;
import com.rav.orderservices.entity.Order;
import com.rav.orderservices.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderAssembler orderAssembler;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient webClient;

    public void placeOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<LineItems> lineItemsList = orderAssembler.assembleOrderRequestDto(orderRequestDto);
        order.setLineItemsList(lineItemsList);

        List<String> itemCodes = order.getLineItemsList().stream()
                .map(LineItems :: getItemCode)
                .collect(Collectors.toList());
        //external call to inventory service to check the availability of items
        InventoryResponseDto[] inventoryResponseDtoArray = webClient.get().uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("itemList", itemCodes).build())
                .retrieve().
                bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseDtoArray).allMatch
                (InventoryResponseDto::isInStock);
        if(allProductsInStock){
            orderRepository.save(order);
        } else{
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }
}
