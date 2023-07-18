package com.market.member.application;

import com.market.common.domain.vo.Address;
import com.market.member.domain.entity.Customer;
import com.market.member.domain.entity.CustomerAddress;
import com.market.member.domain.repository.CustomerAddressRepository;
import com.market.member.domain.repository.CustomerRepository;
import com.market.member.domain.service.MemberService;
import com.market.member.domain.vo.Phone;
import com.market.member.dto.*;
import com.market.member.mapper.CustomerAddressMapper;
import com.market.member.mapper.CustomerMapper;
import com.market.member.mapper.PhoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService {

    private final CustomerMapper customerMapper;
    private final CustomerAddressMapper customerAddressMapper;
    private final PhoneMapper phoneMapper;

    private final MemberService memberService;

    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public List<CustomerDto> getCustomerList(Pageable pageable) {
        return customerRepository.findAll(pageable).stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return customerMapper.toDto(customer);
    }

    public void signUpCustomer(CustomerRegisterDto param) {
        if (memberService.existsEmail(param.getEmail())) {
            throw new DuplicateKeyException("Already exists email.");
        }
        Customer newCustomer = customerMapper.toEntity(param);
        customerRepository.save(newCustomer);
    }

    public void modifyCustomer(Long id, CustomerModifyDto param) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Phone phone = phoneMapper.toEntity(param.getPhoneNumber());
        customer.getInfo().updatePhone(phone);
        customerRepository.save(customer);
    }

    public void withdrawCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<CustomerAddressDto> getAddresses(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return customer.getAddresses().stream()
                .map(customerAddressMapper::toDto)
                .toList();
    }

    public void registerCustomerAddress(Long id, CustomerAddressRegisterDto param) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Address address = customerAddressMapper.toAddress(param);

        CustomerAddress customerAddress = CustomerAddress.builder()
                .address(address)
                .customer(customer)
                .build();
        customerAddressRepository.save(customerAddress);
    }

    public void modifyCustomerAddress(Long addressId, CustomerAddressModifyDto param) {
        CustomerAddress customerAddress = customerAddressRepository.findById(addressId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Address address = customerAddressMapper.toAddress(param);
        customerAddress.updateAddress(address);
        customerAddressRepository.save(customerAddress);
    }
    public void deleteCustomerAddress(Long addressId) {
        customerAddressRepository.deleteById(addressId);
    }

}