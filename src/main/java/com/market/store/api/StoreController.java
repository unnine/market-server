package com.market.store.api;

import com.market.store.application.ItemApplicationService;
import com.market.store.application.StoreApplicationService;
import com.market.store.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreApplicationService storeApplicationService;
    private final ItemApplicationService itemApplicationService;

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
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyStore(@PathVariable Long id, @Valid @RequestBody StoreModifyDto requestDto) {
        storeApplicationService.modifyStore(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeApplicationService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDto>> getItemList(@PathVariable Long id, Pageable pageable) {
        List<ItemDto> items = itemApplicationService.getItemList(id, pageable);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Void> registerItem(@PathVariable Long id, @Valid @RequestBody ItemRegisterDto requestDto) {
        itemApplicationService.registerItem(id, requestDto);
        return ResponseEntity.noContent().build();
    }

}