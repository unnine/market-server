package com.market.member.application;

import com.market.member.domain.entity.Cart;
import com.market.member.domain.entity.CartItem;
import com.market.member.domain.entity.Customer;
import com.market.member.domain.repository.CartItemRepository;
import com.market.member.domain.repository.CustomerRepository;
import com.market.store.domain.entity.Item;
import com.market.store.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartApplicationService {

    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public void addItemToCart(Long customerId, Long itemId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        CartItem cartItem = new CartItem();
        cartItem.add(customer.getCart(), item, 1);
        cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(Long customerId, Long itemId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        CartItem cartItem = getCartItem(customer.getCart(), item);
        cartItemRepository.delete(cartItem);
    }

    public void changeItemCount(Long id, Long itemId, int count) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        CartItem cartItem = getCartItem(customer.getCart(), item);
        cartItem.changeCount(count);
    }

    private CartItem getCartItem(Cart cart, Item item) {
        List<CartItem> cartItems = cart.getCartItems();
        return cartItems.stream()
                .filter(o -> o.getItem().equals(item))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

}
