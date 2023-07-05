package com.market.member.api;

import com.market.member.application.CartApplicationService;
import com.market.member.application.CustomerApplicationService;
import com.market.member.domain.entity.Customer;
import com.market.member.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomerList(Pageable pageable) {
        List<CustomerDto> customers = applicationService.getCustomerList(pageable);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        CustomerDto customer = applicationService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Void> signUpCustomer(@Valid @RequestBody CustomerRegisterDto requestDto) {
        applicationService.signUpCustomer(requestDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyCustomer(@PathVariable Long id, @Valid @RequestBody CustomerModifyDto requestDto) {
        applicationService.modifyCustomer(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawCustomer(@PathVariable Long id) {
        applicationService.withdrawCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<CustomerAddressDto>> getAddresses(@PathVariable Long id) {
        List<CustomerAddressDto> addresses = applicationService.getAddresses(id);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Void> registerAddress(@PathVariable Long id,
                                                @Valid @RequestBody CustomerAddressRegisterDto requestDto) {
        applicationService.registerCustomerAddress(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<Void> modifyAddress(@PathVariable Long addressId,
                                              @Valid @RequestBody CustomerAddressModifyDto requestDto) {
        applicationService.modifyCustomerAddress(addressId, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        applicationService.deleteCustomerAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}