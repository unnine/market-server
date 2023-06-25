package com.market.member.api;

import com.market.member.application.CustomerApplicationService;
import com.market.member.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        return ResponseEntity.ok(applicationService.getCustomerList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUpCustomer(@Valid @RequestBody CustomerDto requestDto) {
        applicationService.signUpCustomer(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto requestDto) {
        applicationService.modifyCustomer(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawCustomer(@PathVariable Long id) {
        applicationService.withdrawCustomer(id);
        return ResponseEntity.ok().build();
    }

}