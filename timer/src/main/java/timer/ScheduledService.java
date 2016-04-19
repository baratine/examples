package timer;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.baratine.service.Cancel;
import io.baratine.service.OnInit;
import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.service.Startup;
import io.baratine.timer.Timers;

@Service
@Startup
public class ScheduledService
{
  private StringBuilder _state = new StringBuilder();

  @Inject
  @Service("timer:")
  private Timers _timer;

  @OnInit
  public void init()
  {
    _timer.runAfter(c->accept(c), 2, TimeUnit.SECONDS, Result.ignore());
  }

  public void accept(Cancel cancel)
  {
    _state.append("timer!\n");
  }

  public String getState()
  {
    String state = _state.toString();
    _state = new StringBuilder();

    return state;
  }
}
