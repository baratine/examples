package inject;

import javax.inject.Inject;

import io.baratine.service.Result;
import io.baratine.service.Service;

@Service
public class HelloService
{
  @Inject
  HelloBean _bean;

  public void test(Result<String> result)
  {
    result.ok(_bean.hello());
  }
}
