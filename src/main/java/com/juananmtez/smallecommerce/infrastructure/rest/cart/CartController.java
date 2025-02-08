package com.juananmtez.smallecommerce.infrastructure.rest.cart;

import com.juananmtez.smallecommerce.domain.exception.CartAlreadyExistsException;
import com.juananmtez.smallecommerce.domain.exception.CartNotFoundException;
import com.juananmtez.smallecommerce.domain.exception.ErrorEnum;
import com.juananmtez.smallecommerce.domain.service.cart.ICartService;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.mapper.ICartRestMapper;
import com.juananmtez.smallecommerce.infrastructure.rest.error.message.ErrorDto;
import com.juananmtez.smallecommerce.infrastructure.rest.product.mapper.IProductRestMapper;
import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final ICartService cartService;

    private final ICartRestMapper cartRestMapper;

    private final IProductRestMapper productRestMapper;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    private static final String LOG_HEADER = "[CONTROLLER][CART]";

    public CartController(
            ICartService cartService,
            @Qualifier("ICartRestMapperImpl") ICartRestMapper cartRestMapper,
            @Qualifier("IProductRestMapperImpl") IProductRestMapper productRestMapper) {
        this.cartService = cartService;
        this.cartRestMapper = cartRestMapper;
        this.productRestMapper = productRestMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        logger.info("{} Get all carts", LOG_HEADER);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cartRestMapper.asCartDtoList(cartService.getAllCarts()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody @NotNull List<@Valid ProductDto> products) {
        logger.info("{} Create cart with products {}", LOG_HEADER, products);
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            cartRestMapper.asCartDto(
                                    cartService.createCart(
                                            productRestMapper.asProductList(products))));

        } catch (CartAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.CART_ALREADY_EXISTS));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable @NotNull UUID id) {
        logger.info("{} Get cart by id {}", LOG_HEADER, id);
        try {
            return ResponseEntity.ok(cartRestMapper.asCartDto(cartService.getCart(id)));

        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.CART_NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }

    @PatchMapping("/{id}/products")
    public ResponseEntity<?> addProductsToCart(
            @PathVariable @NotNull UUID id,
            @RequestBody @NotNull List<@Valid ProductDto> products) {
        logger.info("{} Add products {} to cart with id {}", LOG_HEADER, products, id);

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            cartRestMapper.asCartDto(
                                    cartService.addProductsToCart(
                                            id, productRestMapper.asProductList(products))));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.CART_NOT_FOUND));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable @NotNull UUID id) {
        logger.info("{} Delete cart with id {}", LOG_HEADER, id);

        try {
            cartService.deleteCartById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.CART_NOT_FOUND));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<?> removeProductsFromCart(
            @PathVariable @NotNull UUID id,
            @RequestParam(required = false, value = "product_ids") List<@NotNull UUID> productIds) {
        logger.info(
                "{} Delete products with id {} from cart with id {}", LOG_HEADER, productIds, id);

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            cartRestMapper.asCartDto(
                                    cartService.removeProductsFromCart(id, productIds)));
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.CART_NOT_FOUND));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto(e.getMessage(), ErrorEnum.UNEXPECTED_ERROR));
        }
    }
}
