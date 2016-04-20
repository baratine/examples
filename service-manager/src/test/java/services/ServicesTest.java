package services;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import io.baratine.service.Result;
import io.baratine.service.ResultFuture;
import io.baratine.service.Services;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
public class ServicesTest
{
  @Inject
  private Services _services;

  @org.junit.Test
  public void test()
  {
    ResultFuture<String> result = new ResultFuture<>();

    _services.newService(HelloService.class)
             .api(Hello.class)
             .ref()
             .as(Hello.class)
             .hello(result);

    Assert.assertEquals("Hello World!", result.get(1, TimeUnit.SECONDS));
  }

  public interface Hello
  {
    void hello(Result<String> result);
  }
}
