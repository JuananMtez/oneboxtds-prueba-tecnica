package prueba.tecnica.oneboxtds.infrastructure.repository.cart.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.domain.model.Product;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;
import prueba.tecnica.oneboxtds.infrastructure.repository.product.entity.ProductEntity;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryMapperImplTest {

  @InjectMocks private ICartRepositoryMapperImpl mapper;

  @Test
  void given_CartEntity_convert_to_Cart() {
    CartEntity cartEntity = createCartEntity();

    Cart response = mapper.asCart(cartEntity);

    assertNotNull(response);
    assertEquals(cartEntity.getId(), response.getId());
    assertEquals(cartEntity.getCreationDate(), response.getCreationDate());
    assertEquals(cartEntity.getModificationDate(), response.getModificationDate());

    for (int i = 0; i < response.getProducts().size(); i++) {
      assertEquals(response.getProducts().get(i).getId(), cartEntity.getProducts().get(i).getId());
      assertEquals(
          response.getProducts().get(i).getAmount(), cartEntity.getProducts().get(i).getAmount());
      assertEquals(
          response.getProducts().get(i).getDescription(),
          cartEntity.getProducts().get(i).getDescription());
    }
  }

  @Test
  void given_CartEntityNull_convert_to_Null() {

    Cart response = mapper.asCart(null);
    assertNull(response);
  }

  @Test
  void given_CartEntityListNull_convert_to_Null() {

    List<Cart> response = mapper.asCartList(null);
    assertNull(response);
  }

  @Test
  void given_CartNull_convert_to_Null() {

    CartEntity response = mapper.asCartEntity(null);
    assertNull(response);
  }

  @Test
  void given_CartEntityList_convert_to_CartList() {
    List<CartEntity> cartEntityList = List.of(createCartEntity());

    List<Cart> response = mapper.asCartList(cartEntityList);

    assertNotNull(response);
    assertEquals(response.size(), cartEntityList.size());

    for (int i = 0; i < response.size(); i++) {
      assertEquals(response.get(i).getId(), cartEntityList.get(i).getId());
      assertEquals(response.get(i).getCreationDate(), cartEntityList.get(i).getCreationDate());
      assertEquals(
          response.get(i).getModificationDate(), cartEntityList.get(i).getModificationDate());

      for (int j = 0; j < response.get(i).getProducts().size(); j++) {
        assertEquals(
            response.get(i).getProducts().get(j).getId(),
            cartEntityList.get(i).getProducts().get(j).getId());
        assertEquals(
            response.get(i).getProducts().get(j).getAmount(),
            cartEntityList.get(i).getProducts().get(j).getAmount());
        assertEquals(
            response.get(i).getProducts().get(j).getDescription(),
            cartEntityList.get(i).getProducts().get(j).getDescription());
      }
    }
  }

  @Test
  void given_Cart_convert_to_CartEntity() {
    Cart cart = createCart();

    CartEntity response = mapper.asCartEntity(cart);

    assertNotNull(response);
    assertEquals(response.getId(), cart.getId());
    assertEquals(response.getModificationDate(), cart.getModificationDate());
    assertEquals(response.getCreationDate(), cart.getCreationDate());

    for (int i = 0; i < response.getProducts().size(); i++) {
      assertEquals(response.getProducts().get(i).getId(), cart.getProducts().get(i).getId());
      assertEquals(
          response.getProducts().get(i).getAmount(), cart.getProducts().get(i).getAmount());
      assertEquals(
          response.getProducts().get(i).getDescription(),
          cart.getProducts().get(i).getDescription());
    }
  }

  private CartEntity createCartEntity() {
    return new CartEntity(
        UUID.randomUUID(),
        List.of(createProductEntity()),
        LocalDateTime.now(),
        LocalDateTime.now());
  }

  private ProductEntity createProductEntity() {
    return new ProductEntity(UUID.randomUUID(), "product", new BigDecimal(10));
  }

  private Cart createCart() {
    return new Cart(List.of(createProduct()));
  }

  private Product createProduct() {
    return new Product(UUID.randomUUID(), "product", new BigDecimal(10));
  }
}
