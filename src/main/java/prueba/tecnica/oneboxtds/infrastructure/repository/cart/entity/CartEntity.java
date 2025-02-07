package prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import prueba.tecnica.oneboxtds.infrastructure.repository.product.entity.ProductEntity;

@Getter
@AllArgsConstructor
@Setter
public class CartEntity {

  private UUID id;
  private List<ProductEntity> products;
  private LocalDateTime creationDate;
  private LocalDateTime modificationDate;
}
