package timer;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import com.caucho.junit.ServiceTest;
import com.caucho.junit.TestTime;
import io.baratine.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import timer.ScheduledService;

@RunWith(RunnerBaratine.class)
@ServiceTest(ScheduledService.class)
public class TestScheduledService
{
  @Inject
  @Service
  private ScheduledService _service;

  @Test
  public void test() throws InterruptedException
  {
    TestTime.addTime(1, TimeUnit.SECONDS);
    Thread.sleep(10);
    Assert.assertEquals("", _service.getState());

    TestTime.addTime(2, TimeUnit.SECONDS);
    Thread.sleep(10);

    Assert.assertEquals("timer!\n", _service.getState());
  }
}
