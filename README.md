# vault-java
Java implementation of the [Vault project](https://vaultproject.io/) HTTP API

#### Install
	<dependency>
	 <groupId>se.jhaals</groupId>
	 <artifactId>vault-java</artifactId>
	 <version>0.0.1</version>
	</dependency>

#### Examples

##### Read

    Vault vault = new Vault("http://127.0.0.1:8200", "c7e6d2f3-dc1a-a841-cf29-0cf7bec8ed42");

    VaultResponse vaultResponse = vault.read("aws/creds/deploy");
    System.out.println(vaultResponse.getData().get("access_key"));
    System.out.println(vaultResponse.getData().get("secret_key"));

    System.out.println(vault.read("secret/hello").getData().get("value"));

##### Write

	HashMap<String, String> data = new HashMap<>();
	data.put("value", "hello");
	vault.write("secret/hello", data);

##### Delete

	vault.delete("secret/hello");


