package services;

import io.baratine.service.Result;
import io.baratine.service.Service;

@Service("/hello")
public class HelloService
{
  public void hello(Result<String> result)
  {
    result.ok("Hello World!");
  }
}
