package vault;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.caucho.junit.RunnerBaratine;
import com.caucho.junit.ServiceTest;
import io.baratine.service.ResultFuture;
import io.baratine.service.Service;
import io.baratine.vault.IdAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RunnerBaratine.class)
@ServiceTest(FooAssetVault.class)
public class FooAssetTest
{
  @Inject
  @Service
  private FooAssetVault _vault;

  @Test
  public void test()
  {
    ResultFuture<IdAsset> id = new ResultFuture<>();

    _vault.createWithValue("Hello World!", id);

    Assert.assertEquals("DVS1aMAAR3I", id.get(1, TimeUnit.SECONDS).toString());

    ResultFuture<FooAsset> e = new ResultFuture<>();

    _vault.findByValue("Hello World!", e);

    Assert.assertEquals("DVS1aMAAR3I: Hello World!",
                        e.get(1, TimeUnit.SECONDS).asString());
  }
}
