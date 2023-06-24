package com.market.member.application;

import com.market.member.domain.entity.Seller;
import com.market.member.domain.repository.SellerRepository;
import com.market.member.domain.service.SellerService;
import com.market.member.dto.SellerDto;
import com.market.member.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerApplicationService {

    private final SellerMapper sellerMapper;
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    public List<SellerDto> getSellerList(Pageable pageable) {
        return sellerRepository.findAll(pageable).stream()
                .map(sellerMapper::toDto)
                .toList();
    }

    public SellerDto getSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return sellerMapper.toDto(seller);
    }

    public void signUp(SellerDto param) {
        if (sellerService.existsEmail(param.getEmail())) {
            throw new DuplicateKeyException("Already exists email.");
        }
        Seller seller = sellerMapper.toEntity(param);
        sellerRepository.save(seller);
    }

    public void modifyInfo(Long id, SellerDto param) {
        if (sellerService.existsEmail(param.getEmail())) {
            throw new DuplicateKeyException("Already exists email.");
        }
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        seller.updateInfo(param);
        sellerRepository.save(seller);
    }

    public void withdraw(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        sellerRepository.delete(seller);
    }

}
