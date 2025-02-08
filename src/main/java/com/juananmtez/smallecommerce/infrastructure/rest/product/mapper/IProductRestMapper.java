package com.juananmtez.smallecommerce.infrastructure.rest.product.mapper;

import com.juananmtez.smallecommerce.domain.model.Product;
import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductRestMapper {

    Product asProduct(ProductDto productDto);

    List<Product> asProductList(List<ProductDto> productDtoList);
}
