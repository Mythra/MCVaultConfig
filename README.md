# MC-Vault-Config #

MC-Vault-Config is an extremely small helper library for reading from Hashicorps Vault through
Minecraft Plugins. It should be noted that there are probably better ways of connecting to Vault/
Reading from them than this plugin, or maybe not. It's completely up to you.

If you don't know what Hashicorps Vault is I recommend you go to their website, and read up on
it [here][hashicorp_vault]. Essentially it's a way of managing secrets at an Enterprise level
scale, or in this case many many minecraft servers. Maybe even one.

## Why is this needed? ##

Chances are if you're asking this question you probably don't need it _just yet_. The point is
for a _long long_ time Minecraft has had no way to adequately store "secrets", or things you don't
want other people to read. Sure you can just not give them access to your servers, but I mean come on.
FTPing (not even SFTP) a plaintext file is not the proper way to "deploy" (if you even want to call that deploying)
secrets. Maybe you SSH onto the box, that works fine when you have a small number of boxen, but not really
on a large scale (unless you're super super skilled at gnu parallel ;), and don't mind long bash commands ).
Still I personally feel like we can do better deployments of secrets at a wide scale.

Also it just feels nicer when your secrets are encrypted, rather then sitting plaintext on every developer
box, and production box out there. I mean it just feels inherently wrong.

## Well that's cool, how do I use it? ##

Chances are if you're a server owner (and not developer) you're here because another plugin relies on
this for secret storage. Which is awesome! Here's something to know. If you want to run the plugin with
Vault secret storage note you'll need another server (even something cheap), or _at the minimum_ ssh+root
access to your current server. It's highly recommended though you get another server (something cheap),
and use Vault that way. If you can't meet these requirements, that's a bummer. Maybe there's another secure
solution that you could figure out. After all this isn't for everyone.

If you're not a server owner, maybe you're an architect for a particular server/network. In which case awesome!
If you've been thinking about a better storage solution this _may very well be it_. Even if you don't use
this exact plugin I do hope you'll find a solution (_maybe Keywhiz or KMS_) that allows you to have better
secret storage, and distribution.

If you're a developer. That's awesome! You're probably here deciding if your plugin should depend on this.
In which case here's some tips as to what exactly is needed to run vault:
1. Another Server.
That's it! _Technically_ you can run vault on the same server as your Minecraft Server, but it's probably
best if you didn't. Vault is written in Go, and is pretty performant. Meaning it can even run on a pretty
small box. If you think your target audience has a spare box lying around feel free to _enforce_ secrets
over Vault (Bringing the community one step closer to a more secure server lifestyle). If not, why not
make it optional? Still helping those who want secure storage, without hurting those who don't.

## First Time Setup ##

If this is your first time setup, welcome, welcome come, and sit on down. You remember that other box
that you were going to install vault on? Go ahead, and install that first, the instructions are
located: [here][hashicorp_vault_install].

_Did that?_ **Awesome**.

Now go ahead, and grab a download link for this plugin. (Like from the releases page on github).
After that drag it into your spigot plugin folder. Now you're going to need to set some config values,
but before you go and set those in `config.yml`, hold on up there. That'd kind of ruin the whole point
wouldn't it? ;)
The recommended way to pass this info into your application is through environment variables.
The environment variables you can set are:

| Env Variable       | Function                                                                                                      |
|:-------------------|:--------------------------------------------------------------------------------------------------------------|
| VAULT_ADDR         | The address of the vault server.                                                                              |
| VAULT_TOKEN        | The initial root token for the Vault API example: https://www.vaultproject.io/intro/getting-started/apis.html |
| VAULT_OPEN_TIMEOUT | How long before we stop trying to open a connection to vault.                                                 |
| VAULT_READ_TIMEOUT | How long before we try to stop readnig from vault.                                                            |
| VAULT_SSL_CERT     | The location of the SSL Cert (.pem) if you're using a custom one.                                             |
| VAULT_SSL_VERIFY   | Whether or not to verify SSL (should always be true).                                                         |

Out of all of those the only two required are `VAULT_ADDR`, and `VAULT_TOKEN`. (The initial root token).
Simply spin up your minecraft server with those environment variables, and you're off to the races.

[hashicorp_vault]: https://www.vaultproject.io
[hashicorp_vault_install]: https://www.vaultproject.io/docs/install/install.html
