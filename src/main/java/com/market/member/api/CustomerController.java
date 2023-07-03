package com.market.member.api;

<<<<<<< HEAD
import com.market.member.application.CustomerApplicationService;
import com.market.member.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomerList() {
        return ResponseEntity.ok(customerApplicationService.getCustomerList());
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
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
public class MemberController {


>>>>>>> 9a65deb ([WIP] member controller)

}