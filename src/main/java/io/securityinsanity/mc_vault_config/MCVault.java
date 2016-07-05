package io.securityinsanity.mc_vault_config;

import org.bukkit.plugin.java.JavaPlugin;

public class MCVault extends JavaPlugin {

  private static MCVault instance;
  private VaultReader nsa;

  @Override
  public void onEnable() {
    instance = this;
    nsa = new VaultReader(getConfig().getString("address"), getConfig().getString("token"));
  }

  @Override
  public void onDisable() {
    nsa = null;
    instance = null;
  }

  public static MCVault get( ) {
    return instance;
  }

  public VaultReader getVaultReader() {
    return nsa;
  }
}
