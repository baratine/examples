package events;

public class EventConsumer implements EventListener
{
  private StringBuilder _state = new StringBuilder();

  @Override
  public void event(String data)
  {
    _state.append(data);
  }

  public String getState()
  {
    String state = _state.toString();
    _state = new StringBuilder();

    return state;
  }
}
