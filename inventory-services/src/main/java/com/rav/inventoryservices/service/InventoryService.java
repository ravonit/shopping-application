package com.rav.inventoryservices.service;

import com.rav.inventoryservices.dto.InventoryResponseDto;
import com.rav.inventoryservices.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> itemList){
        return inventoryRepository.findByItemCodeIn(itemList).stream()
                .map(inventory ->
                    InventoryResponseDto.builder()
                            .itemCode(inventory.getItemCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()).collect(Collectors.toList());
    }
}
