package pipe.basic;

import com.caucho.junit.RunnerBaratine;
import io.baratine.service.Services;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
public class TestBasicPipe
{
  @Test
  public void testBasic() throws InterruptedException
  {
    Services manager = Services.newManager().start();

    PublishingService pipeServer =
      manager.newService(PublishingService.class).as(PublishingService.class);

    ReceivingService test =
      manager.newService(new ReceivingService(pipeServer))
             .as(ReceivingService.class);

    test.test();

    Thread.sleep(100);

    Assert.assertEquals(
      "\nsubscribe [baratine-xx,ServiceRefLocal[anon:PublishingService]]"
      + "\nready [baratine-xx,ServiceRefLocal[anon:PublishingService]]"
      + "\nnext hello [baratine-xx,ServiceRefLocal[anon:ReceivingService]]"
      + "\nclose [baratine-xx,ServiceRefLocal[anon:ReceivingService]]",
      State.state());
  }

}
