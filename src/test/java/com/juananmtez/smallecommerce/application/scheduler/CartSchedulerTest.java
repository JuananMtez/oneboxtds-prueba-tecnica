package com.juananmtez.smallecommerce.application.scheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.juananmtez.smallecommerce.domain.service.cart.ICartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartSchedulerTest {

    @InjectMocks private CartScheduler cartScheduler;

    @Mock private ICartService cartService;

    @BeforeEach
    public void init() {
        cartScheduler.setExpiration(10);
    }

    @Test
    public void removeUnusedCarts_success() {
        cartScheduler.removeUnusedCarts();
        verify(cartService, times(1)).deleteUnusedCart(cartScheduler.getExpiration());
    }
}
