package com.market.store.domain.repository;

import com.market.store.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByStoreId(Long storeId);

}