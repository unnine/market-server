package com.market.member.api;

import com.market.member.application.CustomerApplicationService;
import com.market.member.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomerList(Pageable pageable) {
        return ResponseEntity.ok(customerApplicationService.getCustomerList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerApplicationService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody CustomerDto requestDto) {
        customerApplicationService.signUp(requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> signUp(@PathVariable Long id, @Valid @RequestBody CustomerDto requestDto) {
        customerApplicationService.modifyInfo(id, requestDto);
        return ResponseEntity.ok().build();
    }

}