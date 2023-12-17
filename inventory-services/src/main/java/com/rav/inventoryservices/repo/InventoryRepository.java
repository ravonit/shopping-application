package com.rav.inventoryservices.repo;

import com.rav.inventoryservices.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByItemCodeIn(List<String> itemList);
}
