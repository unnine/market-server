package com.market.store.api;

import com.market.store.application.StoreApplicationService;
import com.market.store.dto.StoreDto;
import com.market.store.dto.StoreModifyDto;
import com.market.store.dto.StoreRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreApplicationService storeApplicationService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getStoreList(Pageable pageable) {
        List<StoreDto> stores = storeApplicationService.getStoreList(pageable);
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStore(@PathVariable Long id) {
        StoreDto store = storeApplicationService.getStore(id);
        return ResponseEntity.ok(store);
    }

    @PostMapping
    public ResponseEntity<Void> registerStore(@Valid @RequestBody StoreRegisterDto requestDto) {
        storeApplicationService.registerStore(requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyStore(@PathVariable Long id, @Valid @RequestBody StoreModifyDto requestDto) {
        storeApplicationService.modifyStore(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStore(@PathVariable Long id) {
        storeApplicationService.removeStore(id);
        return ResponseEntity.ok().build();
    }

}
