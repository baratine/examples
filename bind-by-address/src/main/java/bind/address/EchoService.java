package bind.address;

import io.baratine.service.Result;

/**
 * Provides interface for calling EchoServiceImpl
 */
public interface EchoService
{
  public void echo(String value, Result<String> result);
}
