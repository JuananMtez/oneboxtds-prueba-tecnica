package prueba.tecnica.oneboxtds.infrastructure.rest.cart.message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import prueba.tecnica.oneboxtds.infrastructure.rest.product.message.ProductDto;

@Getter
@Setter
@AllArgsConstructor
public class CartDto {
  private UUID id;
  private List<ProductDto> products;
  private LocalDateTime creationDate;
  private LocalDateTime modificationDate;
}
