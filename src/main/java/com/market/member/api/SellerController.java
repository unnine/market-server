package com.market.member.api;

import com.market.member.application.SellerApplicationService;
import com.market.member.dto.SellerDto;
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
        return ResponseEntity.ok(applicationService.getSeller(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SellerDto requestDto) {
        applicationService.signUp(requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> signUp(@PathVariable Long id, @Valid @RequestBody SellerDto requestDto) {
        applicationService.modifyInfo(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable Long id) {
        applicationService.withdraw(id);
        return ResponseEntity.ok().build();
    }

}
