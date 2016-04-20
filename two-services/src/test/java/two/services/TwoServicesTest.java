package two.services;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import com.caucho.junit.ServiceTest;
import io.baratine.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
@ServiceTest(FooService.class)
@ServiceTest(BarService.class)
public class TwoServicesTest
{
  @Inject
  @Service("/foo")
  private TestService _foo;

  @Inject
  @Service("/bar")
  private TestService _bar;

  @Test
  public void test()
  {
    Assert.assertEquals("Hello ServiceRefLocal[/foo]", _foo.test());

    Assert.assertEquals("Hello ServiceRefLocal[/bar]", _bar.test());
  }

  public interface TestService
  {
    String test();
  }
}
