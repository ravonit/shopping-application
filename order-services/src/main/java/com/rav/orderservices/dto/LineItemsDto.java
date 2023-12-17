package com.rav.orderservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsDto {

    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
