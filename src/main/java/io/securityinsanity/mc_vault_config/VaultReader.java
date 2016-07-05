package io.securityinsanity.mc_vault_config;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultReader {

  private VaultConfig config;
  private Vault vault;

  public VaultReader(String potentialAddress, String potentialToken) {
    this.config = new VaultConfig();
    if(potentialAddress != null && !potentialAddress.isEmpty()) {
      this.config.address(potentialAddress);
    }
    if(potentialToken != null && !potentialToken.isEmpty()) {
      this.config.token(potentialToken);
    }
    this.vault = new Vault(this.config);
  }

  public LogicalResponse getResponse(String key) throws VaultException {
    if(key != null && !key.isEmpty()) {
      return this.vault.logical().read(key);
    }
    return null;
  }

  public LogicalResponse getResponse(String key, int retryAmount, int retryWaitMS) throws VaultException {
    if(key != null && !key.isEmpty()) {
      return this.vault.withRetries(retryAmount, retryWaitMS).logical().read(key);
    }
    return null;
  }

  public String readValue(String key, String valueKey) throws VaultException {
    if(key != null && !key.isEmpty() && valueKey != null && !valueKey.isEmpty()) {
      return this.vault.logical().read(key).getData().get(valueKey);
    }
    return null;
  }

  public String readValue(String key, String valueKey, int retryAmount, int retryWaitMS) throws VaultException {
    if(key != null && !key.isEmpty() && valueKey != null && !valueKey.isEmpty()) {
      return this.vault.withRetries(retryAmount, retryWaitMS).logical().read(key).getData().get(valueKey);
    }
    return null;
  }
}
