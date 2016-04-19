package bind.address;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import bind.address.EchoService;
import bind.address.EchoServiceImpl;
import com.caucho.junit.RunnerBaratine;
import com.caucho.junit.ServiceTest;
import io.baratine.service.ResultFuture;
import io.baratine.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
@ServiceTest(EchoServiceImpl.class)
public class ServiceAddressTest
{
  /**
   * Inject Proxy providing access to EchoServiceImpl
   *
   * Note that EchoServiceImpl does not directly implement EchoService
   * interface, but for proxy to work the EchoServiceImpl must provide
   * methods that match signatures in the EchoService.
   */
  @Inject
  @Service("/echo")
  private EchoService _echo;

  @Test
  public void test()
  {
    ResultFuture<String> result = new ResultFuture<>();

    _echo.echo("Hello World!", result);

    Assert.assertEquals("Hello World!", result.get(1, TimeUnit.SECONDS));
  }
}
