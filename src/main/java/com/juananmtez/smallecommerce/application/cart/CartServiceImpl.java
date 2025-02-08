package com.juananmtez.smallecommerce.application.cart;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.domain.repository.cart.ICartRepository;
import com.juananmtez.smallecommerce.domain.service.cart.ICartService;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private static final String LOG_HEADER = "[APPLICATION][CART]";

    private final ICartRepository cartRepository;

    public CartServiceImpl(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(List<Product> products) {
        logger.info("{} Create new cart with products {}", LOG_HEADER, products);

        return cartRepository.saveCart(new Cart(products));
    }

    @Override
    public Cart removeProductsFromCart(UUID cartId, List<UUID> productIds) {

        logger.info("{} Remove products from cart with id {}", LOG_HEADER, productIds);

        Cart cart = cartRepository.findCartById(cartId);

        cart.removeProductsByIds(productIds);

        return cartRepository.updateCart(cart);
    }

    @Override
    public void deleteUnusedCart(int expiration) {
        logger.info("{} Delete unused carts", LOG_HEADER);

        cartRepository.deleteAllUnusedCarts(expiration);
    }

    @Override
    public Cart getCart(UUID cartId) {
        logger.info("{} Get cart with id {}", LOG_HEADER, cartId);
        return cartRepository.findCartById(cartId);
    }

    @Override
    public List<Cart> getAllCarts() {
        logger.info("{} Get all carts", LOG_HEADER);
        return cartRepository.findAllCarts();
    }

    @Override
    public Cart addProductsToCart(UUID cartId, List<Product> products) {
        logger.info("{} Add products {} to cart with id {}", LOG_HEADER, products, cartId);
        Cart cart = cartRepository.findCartById(cartId);
        cart.addProducts(products);
        return cartRepository.updateCart(cart);
    }

    @Override
    public void deleteCartById(UUID cartId) {
        logger.info("{} Delete cart with id {}", LOG_HEADER, cartId);
        cartRepository.deleteCartById(cartId);
    }
}
