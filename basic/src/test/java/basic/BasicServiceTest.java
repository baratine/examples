package basic;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import com.caucho.junit.ServiceTest;
import io.baratine.service.ResultFuture;
import io.baratine.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
@ServiceTest(BasicService.class)
public class BasicServiceTest
{
  //Inject Proxy providing access to BasicService
  @Inject @Service
  private BasicService _service;

  @Test
  public void test()
  {
    ResultFuture<String> result = new ResultFuture<>();

    _service.test(result);

    System.out.println("BasicServiceTest.test " + _service.getClass());

    Assert.assertEquals("Hello World!", result.get(1, TimeUnit.SECONDS));
  }
}
