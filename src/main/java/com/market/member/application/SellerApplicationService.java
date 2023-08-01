package com.market.member.application;

import com.market.common.util.Cipher;
import com.market.member.domain.entity.Seller;
import com.market.member.domain.repository.SellerRepository;
import com.market.member.domain.service.MemberService;
import com.market.member.domain.vo.MemberInfo;
import com.market.member.domain.vo.Phone;
import com.market.member.dto.SellerDto;
import com.market.member.dto.SellerModifyDto;
import com.market.member.dto.SellerRegisterDto;
import com.market.member.mapper.MemberInfoMapper;
import com.market.member.mapper.PhoneMapper;
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
    private final PhoneMapper phoneMapper;

    private final MemberService memberService;

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

    public void signUpSeller(SellerRegisterDto param) {
        if (memberService.existsEmail(param.getEmail())) {
            throw new DuplicateKeyException("Already exists email.");
        }
        Seller seller = sellerMapper.toEntity(param);
        MemberInfo memberInfo = seller.getInfo();
        String encryptedPassword = Cipher.encode(memberInfo.getPassword());
        memberInfo.updatePassword(encryptedPassword);
        sellerRepository.save(seller);
    }

    public void modifySeller(Long id, SellerModifyDto param) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Phone phone = phoneMapper.toEntity(param.getPhoneNumber());
        seller.getInfo().updatePhone(phone);
        sellerRepository.save(seller);
    }

    public void withdrawSeller(Long id) {
        sellerRepository.deleteById(id);
    }

}