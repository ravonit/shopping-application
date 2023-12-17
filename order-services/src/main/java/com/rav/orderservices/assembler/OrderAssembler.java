package com.rav.orderservices.assembler;

import com.rav.orderservices.dto.LineItemsDto;
import com.rav.orderservices.dto.OrderRequestDto;
import com.rav.orderservices.entity.LineItems;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAssembler {

    public List<LineItems> assembleOrderRequestDto(OrderRequestDto orderRequestDto) {
        List<LineItems> lineItemsList = orderRequestDto.getLineItemsDtoLIst()
                .stream()
                .map(this::mapToEntity).collect(Collectors.toList());
        return lineItemsList;
    }

    private LineItems mapToEntity(LineItemsDto lineItemsDto) {
        LineItems lineItems = new LineItems();
        lineItems.setPrice(lineItemsDto.getPrice());
        lineItems.setQuantity(lineItemsDto.getQuantity());
        lineItems.setItemCode(lineItemsDto.getItemCode());
        return lineItems;
    }
}
