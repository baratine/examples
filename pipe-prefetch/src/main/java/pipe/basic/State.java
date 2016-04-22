package pipe.basic;

import io.baratine.service.ServiceRef;

public class State
{
  private static StringBuilder _state = new StringBuilder("");

  public static void state(String state)
  {
    _state.append(state);
  }

  public static String state()
  {
    StringBuilder builder = _state;
    _state = new StringBuilder("");

    return builder.toString();
  }

  public static String debugService()
  {
    return debugService(threadName());
  }

  public static String threadName()
  {
    String name = Thread.currentThread().getName();

    return name.replaceAll("-\\d+", "-xx");
  }

  public static String debugService(String threadName)
  {
    return " [" + threadName + "," + ServiceRef.current() + "]";
  }
}
