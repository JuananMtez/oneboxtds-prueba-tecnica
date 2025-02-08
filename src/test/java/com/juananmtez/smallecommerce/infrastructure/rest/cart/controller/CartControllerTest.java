package com.juananmtez.smallecommerce.infrastructure.rest.cart.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.juananmtez.smallecommerce.domain.exception.CartAlreadyExistsException;
import com.juananmtez.smallecommerce.domain.exception.CartNotFoundException;
import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.domain.service.cart.ICartService;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.CartController;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.mapper.ICartRestMapper;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.message.CartDto;
import com.juananmtez.smallecommerce.infrastructure.rest.error.message.ErrorDto;
import com.juananmtez.smallecommerce.infrastructure.rest.product.mapper.IProductRestMapper;
import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @InjectMocks private CartController cartController;

    @Mock private ICartService cartService;

    @Mock private ICartRestMapper cartRestMapper;

    @Mock private IProductRestMapper productRestMapper;

    @Test
    public void createCart_success() {
        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());
        Cart cart = createCart();

        CartDto cartDto = createCartDto();

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.createCart(productList)).thenReturn(cart);
        when(cartRestMapper.asCartDto(cart)).thenReturn(cartDto);

        ResponseEntity<?> response = cartController.createCart(productDtoList);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(CartDto.class, response.getBody().getClass());

        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).createCart(productList);
        verify(cartRestMapper).asCartDto(cart);
    }

    @Test
    public void createCart_CartAlreadyExist() {
        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.createCart(productList)).thenThrow(CartAlreadyExistsException.class);

        ResponseEntity<?> response = cartController.createCart(productDtoList);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).createCart(productList);
    }

    @Test
    public void createCart_UnexpectedError() {
        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.createCart(productList)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = cartController.createCart(productDtoList);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());
        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).createCart(productList);
    }

    @Test
    public void getCart_Success() {
        UUID cartId = UUID.randomUUID();
        Cart cart = createCart();
        CartDto cartDto = createCartDto();

        when(cartService.getCart(cartId)).thenReturn(cart);
        when(cartRestMapper.asCartDto(cart)).thenReturn(cartDto);

        ResponseEntity<?> response = cartController.getCart(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(CartDto.class, response.getBody().getClass());

        verify(cartService).getCart(cartId);
        verify(cartRestMapper).asCartDto(cart);
    }

    @Test
    public void getCart_NotFound() {
        UUID cartId = UUID.randomUUID();

        when(cartService.getCart(cartId)).thenThrow(CartNotFoundException.class);

        ResponseEntity<?> response = cartController.getCart(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).getCart(cartId);
    }

    @Test
    public void getCart_UnexpectedError() {
        UUID cartId = UUID.randomUUID();

        when(cartService.getCart(cartId)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = cartController.getCart(cartId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).getCart(cartId);
    }

    @Test
    public void addProductToCart_success() {
        UUID cartId = UUID.randomUUID();

        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());
        Cart cart = createCart();

        CartDto cartDto = createCartDto();

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.addProductsToCart(cartId, productList)).thenReturn(cart);
        when(cartRestMapper.asCartDto(cart)).thenReturn(cartDto);

        ResponseEntity<?> response = cartController.addProductsToCart(cartId, productDtoList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(CartDto.class, response.getBody().getClass());

        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).addProductsToCart(cartId, productList);
        verify(cartRestMapper).asCartDto(cart);
    }

    @Test
    public void addProductToCart_NotFound() {
        UUID cartId = UUID.randomUUID();

        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.addProductsToCart(cartId, productList))
                .thenThrow(CartNotFoundException.class);

        ResponseEntity<?> response = cartController.addProductsToCart(cartId, productDtoList);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).addProductsToCart(cartId, productList);
    }

    @Test
    public void addProductToCart_UnexpectedError() {
        UUID cartId = UUID.randomUUID();

        List<ProductDto> productDtoList = List.of(createProductDto());
        List<Product> productList = List.of(createProduct());

        when(productRestMapper.asProductList(productDtoList)).thenReturn(productList);
        when(cartService.addProductsToCart(cartId, productList)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = cartController.addProductsToCart(cartId, productDtoList);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(productRestMapper).asProductList(productDtoList);
        verify(cartService).addProductsToCart(cartId, productList);
    }

    @Test
    public void deleteCartById_success() {
        UUID cartId = UUID.randomUUID();

        ResponseEntity<?> response = cartController.deleteCartById(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(cartService).deleteCartById(cartId);
    }

    @Test
    public void deleteCartById_NotFound() {
        UUID cartId = UUID.randomUUID();

        doThrow(new CartNotFoundException("Cart not found"))
                .when(cartService)
                .deleteCartById(cartId);

        ResponseEntity<?> response = cartController.deleteCartById(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).deleteCartById(cartId);
    }

    @Test
    public void deleteCartById_UnexpectedError() {
        UUID cartId = UUID.randomUUID();

        doThrow(new RuntimeException()).when(cartService).deleteCartById(cartId);

        ResponseEntity<?> response = cartController.deleteCartById(cartId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).deleteCartById(cartId);
    }

    @Test
    public void removeProductsFromCart_success() {
        UUID cartId = UUID.randomUUID();
        List<UUID> productIds = List.of(UUID.randomUUID());
        Cart cart = createCart();

        CartDto cartDto = createCartDto();

        when(cartService.removeProductsFromCart(cartId, productIds)).thenReturn(cart);
        when(cartRestMapper.asCartDto(cart)).thenReturn(cartDto);

        ResponseEntity<?> response = cartController.removeProductsFromCart(cartId, productIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(CartDto.class, response.getBody().getClass());

        verify(cartService).removeProductsFromCart(cartId, productIds);
        verify(cartRestMapper).asCartDto(cart);
    }

    @Test
    public void removeProductsFromCart_NotFound() {
        UUID cartId = UUID.randomUUID();
        List<UUID> productIds = List.of(UUID.randomUUID());

        when(cartService.removeProductsFromCart(cartId, productIds))
                .thenThrow(CartNotFoundException.class);

        ResponseEntity<?> response = cartController.removeProductsFromCart(cartId, productIds);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).removeProductsFromCart(cartId, productIds);
    }

    @Test
    public void removeProductsFromCart_UnexpectedError() {
        UUID cartId = UUID.randomUUID();
        List<UUID> productIds = List.of(UUID.randomUUID());

        when(cartService.removeProductsFromCart(cartId, productIds))
                .thenThrow(RuntimeException.class);

        ResponseEntity<?> response = cartController.removeProductsFromCart(cartId, productIds);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).removeProductsFromCart(cartId, productIds);
    }

    @Test
    public void getAllCarts_success() {
        List<Cart> cartList = List.of(createCart());
        List<CartDto> cartDtoList = List.of(createCartDto());
        when(cartService.getAllCarts()).thenReturn(cartList);
        when(cartRestMapper.asCartDtoList(cartList)).thenReturn(cartDtoList);

        ResponseEntity<?> response = cartController.getAllCarts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(cartService).getAllCarts();
        verify(cartRestMapper).asCartDtoList(cartList);
    }

    @Test
    public void getAllCarts_unexpectedError() {
        when(cartService.getAllCarts()).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = cartController.getAllCarts();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorDto.class, response.getBody().getClass());

        verify(cartService).getAllCarts();
    }

    private ProductDto createProductDto() {
        return new ProductDto(UUID.randomUUID(), "description", new BigDecimal(10));
    }

    private Product createProduct() {
        return new Product(UUID.randomUUID(), "description", new BigDecimal(10));
    }

    private CartDto createCartDto() {
        return new CartDto(
                UUID.randomUUID(),
                List.of(createProductDto()),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    private Cart createCart() {
        return new Cart(
                UUID.randomUUID(),
                List.of(createProduct()),
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}
