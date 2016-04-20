package vault;

import io.baratine.service.Modify;
import io.baratine.service.Result;
import io.baratine.vault.Asset;
import io.baratine.vault.Id;
import io.baratine.vault.IdAsset;

@Asset
public class FooAsset
{
  @Id
  private IdAsset id;
  private String value;

  @Modify
  public void createWithValue(String value, Result<IdAsset> id)
  {
    this.value = value;

    id.ok(this.id);
  }

  public String asString()
  {
    return id + ": " + value;
  }
}
