package com.juananmtez.smallecommerce.infrastructure.rest.cart.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.message.CartDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ICartRestMapperTest {

    @InjectMocks private ICartRestMapperImpl cartRestMapper;

    @Test
    public void asCartDto_success() {
        Cart cart = new Cart(List.of(createProduct()));

        CartDto response = cartRestMapper.asCartDto(cart);

        assertNotNull(response);

        assertEquals(cart.getId(), response.getId());
        assertEquals(cart.getProducts().size(), response.getProducts().size());

        for (int i = 0; i < response.getProducts().size(); i++) {
            assertEquals(response.getProducts().get(i).getId(), cart.getProducts().get(i).getId());
            assertEquals(
                    response.getProducts().get(i).getDescription(),
                    cart.getProducts().get(i).getDescription());
            assertEquals(
                    response.getProducts().get(i).getAmount(),
                    cart.getProducts().get(i).getAmount());
        }
    }

    @Test
    public void asCartDtoList_success() {
        List<Cart> cartList = List.of(new Cart(List.of(createProduct())));

        List<CartDto> response = cartRestMapper.asCartDtoList(cartList);

        assertNotNull(response);
        for (int i = 0; i < response.size(); i++) {
            assertEquals(response.get(i).getId(), cartList.get(i).getId());
            assertEquals(
                    response.get(i).getProducts().size(), cartList.get(i).getProducts().size());
            for (int j = 0; j < response.get(i).getProducts().size(); j++) {
                assertEquals(
                        response.get(i).getProducts().get(j).getId(),
                        cartList.get(i).getProducts().get(j).getId());
                assertEquals(
                        response.get(i).getProducts().get(j).getDescription(),
                        cartList.get(i).getProducts().get(j).getDescription());
                assertEquals(
                        response.get(i).getProducts().get(j).getAmount(),
                        cartList.get(i).getProducts().get(j).getAmount());
            }
        }
    }

    private Product createProduct() {
        return new Product(UUID.randomUUID(), "", BigDecimal.ONE);
    }
}
