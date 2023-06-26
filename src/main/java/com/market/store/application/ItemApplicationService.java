package com.market.store.application;

import com.market.store.domain.entity.Item;
import com.market.store.domain.entity.Store;
import com.market.store.domain.repository.ItemRepository;
import com.market.store.domain.repository.StoreRepository;
import com.market.store.dto.ItemDto;
import com.market.store.dto.ItemModifyDto;
import com.market.store.dto.ItemRegisterDto;
import com.market.store.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemApplicationService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    public List<ItemDto> getItemList(Long storeId) {
        return itemRepository.findAllByStoreId(storeId).stream()
                .map(itemMapper::toDto)
                .toList();
    }

    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return itemMapper.toDto(item);
    }

    public void registerItem(Long storeId, ItemRegisterDto dto) {
        Item item = itemMapper.toEntity(dto);
        Store store = storeRepository.findById(storeId)
                        .orElseThrow(() -> new EmptyResultDataAccessException(1));
        item.registerTo(store);
        itemRepository.save(item);
    }

    public void modifyItem(Long id, ItemModifyDto dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        itemMapper.updateEntity(dto, item);
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

}