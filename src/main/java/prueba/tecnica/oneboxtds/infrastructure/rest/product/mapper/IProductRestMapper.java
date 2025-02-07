package prueba.tecnica.oneboxtds.infrastructure.rest.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import prueba.tecnica.oneboxtds.domain.model.Product;
import prueba.tecnica.oneboxtds.infrastructure.rest.product.message.ProductDto;

@Mapper(componentModel = "spring")
public interface IProductRestMapper {

  Product asProduct(ProductDto productDto);

  List<Product> asProductList(List<ProductDto> productDtoList);
}
