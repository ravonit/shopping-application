package com.rav.orderservices.dto;

import com.rav.orderservices.entity.LineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private List<LineItemsDto> lineItemsDtoLIst;
}
