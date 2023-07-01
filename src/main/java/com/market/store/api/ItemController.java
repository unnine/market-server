package com.market.store.api;

import com.market.store.application.ItemApplicationService;
import com.market.store.dto.ItemDto;
import com.market.store.dto.ItemModifyDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/store/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemApplicationService itemApplicationService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id) {
        ItemDto itemDto = itemApplicationService.getItem(id);
        return ResponseEntity.ok(itemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyItem(@PathVariable Long id, @Valid @RequestBody ItemModifyDto requestDto) {
        itemApplicationService.modifyItem(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemApplicationService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

}