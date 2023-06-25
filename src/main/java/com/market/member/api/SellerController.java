package com.market.member.api;

import com.market.member.application.SellerApplicationService;
import com.market.member.dto.SellerDto;
import com.market.member.dto.SellerModifyDto;
import com.market.member.dto.SellerRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<SellerDto>> getSellerList(Pageable pageable) {
        return ResponseEntity.ok(applicationService.getSellerList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> getSeller(@PathVariable Long id) {
        SellerDto sellerDto = applicationService.getSeller(id);
        return ResponseEntity.ok(sellerDto);
    }

    @PostMapping
    public ResponseEntity<Void> signUpSeller(@Valid @RequestBody SellerRegisterDto requestDto) {
        applicationService.signUpSeller(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifySeller(@PathVariable Long id, @Valid @RequestBody SellerModifyDto requestDto) {
        applicationService.modifySeller(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawSeller(@PathVariable Long id) {
        applicationService.withdrawSeller(id);
        return ResponseEntity.ok().build();
    }

}
