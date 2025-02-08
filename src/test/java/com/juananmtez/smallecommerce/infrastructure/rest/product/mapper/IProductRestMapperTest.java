package com.juananmtez.smallecommerce.infrastructure.rest.product.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IProductRestMapperTest {

    @InjectMocks private IProductRestMapperImpl productRestMapper;

    @Test
    public void asProduct_success() {
        ProductDto productDto = new ProductDto(UUID.randomUUID(), "description", BigDecimal.ONE);

        Product product = productRestMapper.asProduct(productDto);

        assertNotNull(product);

        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getAmount(), product.getAmount());
    }

    @Test
    public void asProductList_success() {
        List<ProductDto> productDtoList =
                List.of(new ProductDto(UUID.randomUUID(), "description", BigDecimal.ONE));

        List<Product> productList = productRestMapper.asProductList(productDtoList);

        assertNotNull(productList);
        assertEquals(productDtoList.size(), productList.size());
        for (int i = 0; i < productDtoList.size(); i++) {
            assertEquals(productDtoList.get(i).getId(), productList.get(i).getId());
            assertEquals(
                    productDtoList.get(i).getDescription(), productList.get(i).getDescription());
            assertEquals(productDtoList.get(i).getAmount(), productList.get(i).getAmount());
        }
    }
}
