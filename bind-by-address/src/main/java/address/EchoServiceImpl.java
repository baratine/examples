package address;

import io.baratine.service.Result;
import io.baratine.service.Service;

@Service("/echo")
public class EchoServiceImpl
{
  public void echo(String value, Result<String> result)
  {
    result.ok(value);
  }
}
