package prueba.tecnica.oneboxtds.application.scheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.tecnica.oneboxtds.domain.service.cart.ICartService;

@ExtendWith(MockitoExtension.class)
public class CartSchedulerTest {

  @InjectMocks private CartScheduler cartScheduler;

  @Mock private ICartService cartService;

  @Test
  public void removeUnusedCarts_success() {
    cartScheduler.removeUnusedCarts();
    verify(cartService, times(1)).deleteUnusedCart();
  }
}
