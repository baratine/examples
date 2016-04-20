package two.services;

import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.service.ServiceRef;

@Service("/bar")
public class BarService
{
  public void test(Result<String> result)
  {
    result.ok("Hello " + ServiceRef.current());
  }
}
