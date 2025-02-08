package com.juananmtez.smallecommerce.application.cart;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.domain.repository.cart.ICartRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks private CartServiceImpl cartService;

    @Mock private ICartRepository cartRepository;

    @Test
    public void createdCart_success() {
        List<Product> products = List.of(createProduct());
        Cart cart = new Cart(UUID.randomUUID(), products, LocalDateTime.now(), LocalDateTime.now());

        when(cartRepository.saveCart(any())).thenReturn(cart);

        Cart response = cartService.createCart(products);

        assertNotNull(response);

        verify(cartRepository).saveCart(any());
    }

    @Test
    public void removeProductsFromCart_success() {
        List<UUID> products = new ArrayList<>();
        products.add(UUID.randomUUID());

        Cart cart = createCart();

        when(cartRepository.findCartById(cart.getId())).thenReturn(cart);

        cartService.removeProductsFromCart(cart.getId(), products);

        verify(cartRepository, times(1)).findCartById(cart.getId());
        verify(cartRepository, times(1)).updateCart(cart);
    }

    @Test
    public void getCart_success() {
        UUID cartId = UUID.randomUUID();

        Cart cart = createCart();

        when(cartRepository.findCartById(cartId)).thenReturn(cart);

        Cart response = cartService.getCart(cartId);

        assertNotNull(response);
        verify(cartRepository, times(1)).findCartById(cartId);
    }

    @Test
    public void addProductToCart_success() {
        UUID cartId = UUID.randomUUID();
        List<Product> products = new ArrayList<>(List.of(createProduct()));

        Cart cart = createCart();

        when(cartRepository.findCartById(cartId)).thenReturn(cart);
        when(cartRepository.updateCart(any())).thenReturn(cart);

        Cart response = cartService.addProductsToCart(cartId, products);

        assertNotNull(response);
        verify(cartRepository, times(1)).findCartById(cartId);
        verify(cartRepository, times(1)).updateCart(cart);
    }

    @Test
    public void deleteCartById_success() {
        UUID cartId = UUID.randomUUID();
        cartService.deleteCartById(cartId);
        verify(cartRepository, times(1)).deleteCartById(cartId);
    }

    @Test
    public void deleteUnusedCart_success() {
        int expiration = 10;

        cartService.deleteUnusedCart(expiration);

        verify(cartRepository, times(1)).deleteAllUnusedCarts(expiration);
    }

    @Test
    public void getAllCarts_success() {
        List<Cart> cartList = List.of(createCart(LocalDateTime.now().minusMinutes(20)));
        when(cartRepository.findAllCarts()).thenReturn(cartList);
        List<Cart> response = cartService.getAllCarts();
        assertNotNull(response);
        verify(cartRepository, times(1)).findAllCarts();
    }

    private Product createProduct() {
        return new Product(UUID.randomUUID(), "product", new BigDecimal(10));
    }

    private Cart createCart() {
        return new Cart(
                UUID.randomUUID(),
                new ArrayList<>(List.of(createProduct())),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    private Cart createCart(LocalDateTime modificationDate) {
        return new Cart(
                UUID.randomUUID(),
                new ArrayList<>(List.of(createProduct())),
                LocalDateTime.now(),
                modificationDate);
    }
}
