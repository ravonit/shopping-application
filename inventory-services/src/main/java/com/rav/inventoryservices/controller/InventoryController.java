package com.rav.inventoryservices.controller;

import com.rav.inventoryservices.dto.InventoryResponseDto;
import com.rav.inventoryservices.repo.InventoryRepository;
import com.rav.inventoryservices.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> itemList){
        return inventoryService.isInStock(itemList);
    }

}
