package address;

import io.baratine.service.Result;

public interface EchoService
{
  public void echo(String value, Result<String> result);
}
