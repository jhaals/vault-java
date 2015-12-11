import junit.framework.TestCase;

import java.util.HashMap;

public class VaultTest extends TestCase {

    private String token = System.getenv("VAULT_TOKEN");

    public void testWrite() throws Exception {
        Vault vault = new Vault("http://127.0.0.1:8200", token);
        HashMap<String, String> data = new HashMap<>();
        data.put("value", "hello");
        vault.write("secret/hello", data);
    }

    public void testRead() throws Exception {
        Vault vault = new Vault("http://127.0.0.1:8200", token);
        VaultResponse result = vault.read("secret/hello");
        assertEquals(result.getData().get("value"), "hello");
    }

    public void testDelete() throws Exception {
        Vault vault = new Vault("http://127.0.0.1:8200", token);
        vault.delete("secret/hello");
        try {
            vault.read("secret/hello");
            fail("Expected VaultException");
        } catch (VaultException e) {
            assertEquals(e.getStatusCode(), 404);

        }
    }

    public void testReadWithInvalidToken() throws Exception {
        Vault vault = new Vault("http://127.0.0.1:8200", "invalid");
        try {
            vault.read("secret/hello");
            fail("Expected VaultException");
        } catch (VaultException e) {
            assertEquals(e.getStatusCode(), 403);
        }
    }

}