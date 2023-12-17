package com.rav.orderservices.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tm_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
