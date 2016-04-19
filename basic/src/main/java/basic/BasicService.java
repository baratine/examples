package basic;

import io.baratine.service.Result;
import io.baratine.service.Service;

/**
 * Raw service
 */
@Service
public class BasicService
{
  public void test(Result<String> result)
  {
    result.ok("Hello World!");
  }
}
