package com.rav.inventoryservices.service;

import com.rav.inventoryservices.dto.InventoryResponseDto;
import com.rav.inventoryservices.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> itemList){
        LOGGER.info("Entered isInStock method");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        LOGGER.info("Wait ended");
        return inventoryRepository.findByItemCodeIn(itemList).stream()
                .map(inventory ->
                    InventoryResponseDto.builder()
                            .itemCode(inventory.getItemCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()).collect(Collectors.toList());
    }
}
