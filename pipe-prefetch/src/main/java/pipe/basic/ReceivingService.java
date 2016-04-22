package pipe.basic;

import static pipe.basic.State.debugService;
import static pipe.basic.State.state;

public class ReceivingService
{
  private PublishingService _server;

  public ReceivingService()
  {
  }

  ReceivingService(PublishingService receiver)
  {
    _server = receiver;
  }

  public void test()
  {
    try {
      _server.hello((next, exception, close) -> {
        if (exception != null) {
          state("\nclient-exn: " + exception);
        }
        else if (close) {
          state("\nclose" + debugService());

        }
        else {
          state("\nnext " + next + debugService());
        }
      });
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}
