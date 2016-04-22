package pipe.basic;

import static pipe.basic.State.debugService;
import static pipe.basic.State.state;

import io.baratine.pipe.Credits;
import io.baratine.pipe.Pipe;
import io.baratine.pipe.ResultPipeIn;

public class PublishingService implements Credits.OnAvailable
{
  private Pipe<String> _pipe;

  public void hello(ResultPipeIn<String> inPipe)
  {
    state("\nsubscribe" + debugService());

    _pipe = inPipe.pipe();

    _pipe.credits().onAvailable(this);

    inPipe.ok(null);
  }

  @Override
  public void available()
  {
    state("\nready" + debugService());
    _pipe.next("hello");
    _pipe.close();
  }
}
