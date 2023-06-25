package com.market.store.application;

import com.market.member.domain.entity.Seller;
import com.market.member.domain.repository.SellerRepository;
import com.market.store.domain.entity.Store;
import com.market.store.domain.repository.StoreRepository;
import com.market.store.dto.StoreDto;
import com.market.store.dto.StoreModifyDto;
import com.market.store.dto.StoreRegisterDto;
import com.market.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StoreApplicationService {

    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;

    public List<StoreDto> getStoreList(Pageable pageable) {
        return storeRepository.findAll(pageable).stream()
                .map(storeMapper::toDto)
                .toList();
    }

    public StoreDto getStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return storeMapper.toDto(store);
    }

    public void registerStore(StoreRegisterDto param) {
        Long ownerId = param.getOwnerId();

        Objects.requireNonNull(ownerId);

        Seller seller =  sellerRepository.findById(ownerId).orElseThrow(() -> new EmptyResultDataAccessException(1));
        Store store = storeMapper.toEntityForRegister(param, seller);
        storeRepository.save(store);
    }

    public void modifyStore(Long id, StoreModifyDto param) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        storeMapper.updateStore(param, store);
        storeRepository.save(store);
    }

    public void removeStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        storeRepository.delete(store);
    }

}
