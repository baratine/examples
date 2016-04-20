package vault;

import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.vault.IdAsset;
import io.baratine.vault.Vault;

@Service
public interface FooAssetVault extends Vault<IdAsset,FooAsset>
{
  void createWithValue(String value, Result<IdAsset> id);

  void findByValue(String value, Result<FooAsset> result);
}

