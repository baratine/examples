package bind.address;

import io.baratine.service.Result;
import io.baratine.service.Service;

/**
 * This example demonstrates that Services do not necessarily need to implement
 * a client interface.
 */
@Service("/echo")
public class EchoServiceImpl
{
  public void echo(String value, Result<String> result)
  {
    result.ok(value);
  }
}
