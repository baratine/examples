package events;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import io.baratine.event.Events;
import io.baratine.service.Result;
import io.baratine.service.ResultFuture;
import io.baratine.service.Service;
import io.baratine.service.ServiceRef;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
public class EventsTest
{
  @Inject
  @Service("event:")
  private Events _events;

  /**
   * Events can be published to an consumer instance bound by interface
   *
   * @throws InterruptedException
   */
  @Test
  public void testBindingWithApi() throws InterruptedException
  {
    EventConsumer consumer = new EventConsumer();

    _events.consumer(EventListener.class, consumer, Result.ignore());

    ResultFuture<EventListener> result = new ResultFuture<>();

    _events.publisher(EventListener.class, result);

    EventListener publisher = result.get(1, TimeUnit.SECONDS);

    publisher.event("Hello World!");

    ServiceRef.flushOutbox();

    Thread.sleep(10);

    Assert.assertEquals("Hello World!", consumer.getState());
  }

  /**
   * Events can be published to an instance bound by path
   *
   * @throws InterruptedException
   */
  @Test
  public void testBindingWithPath() throws InterruptedException
  {
    EventConsumer fooConsumer = new EventConsumer();
    EventConsumer barConsumer = new EventConsumer();

    // receive foo events
    _events.consumer("foo", fooConsumer, Result.ignore());

    // receive bar events
    _events.consumer("bar", barConsumer, Result.ignore());

    ResultFuture<EventListener> fooPublisherResult = new ResultFuture<>();
    _events.publisherPath("foo", EventListener.class, fooPublisherResult);
    fooPublisherResult.get(1, TimeUnit.SECONDS).event("Hello Foo World!");

    ResultFuture<EventListener> barPublisherResult = new ResultFuture<>();
    _events.publisherPath("bar", EventListener.class, barPublisherResult);
    barPublisherResult.get(1, TimeUnit.SECONDS).event("Hello Bar World!");

    ServiceRef.flushOutbox();

    Thread.sleep(10);

    Assert.assertEquals("Hello Foo World!", fooConsumer.getState());
    Assert.assertEquals("Hello Bar World!", barConsumer.getState());
  }

  /**
   * Subscribing will deliver event to all subscribed listeners
   *
   * @throws InterruptedException
   */
  @Test
  public void testSubscribe() throws InterruptedException
  {
    EventConsumer fooConsumerOne = new EventConsumer();
    EventConsumer fooConsumerTwo = new EventConsumer();

    _events.subscriber("foo", fooConsumerOne, Result.ignore());
    _events.subscriber("foo", fooConsumerTwo, Result.ignore());

    ResultFuture<EventListener> fooPublisherResult = new ResultFuture<>();
    _events.publisherPath("foo", EventListener.class, fooPublisherResult);
    fooPublisherResult.get(1, TimeUnit.SECONDS).event("Hello Foo World!");

    ServiceRef.flushOutbox();

    Thread.sleep(10);

    Assert.assertEquals("Hello Foo World!", fooConsumerOne.getState());
    Assert.assertEquals("Hello Foo World!", fooConsumerTwo.getState());
  }
}
