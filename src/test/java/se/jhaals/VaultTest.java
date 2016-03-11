package se.jhaals;

import junit.framework.TestCase;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;


public class VaultTest extends TestCase {

    private String token = "7efdbf54-cdb3-70bf-3b9e-96b52f0f50dd"; //System.getenv("VAULT_TOKEN");
    private String vault_server_url = "http://192.168.99.100:8200";
    private Vault vault;

    @Before
    public void setUp() {
        this.vault = new Vault(vault_server_url, token);

    }

    public void testWrite() throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("value", "hello");
        vault.write("secret/hello", data);
    }

    public void testRead() throws Exception {
        VaultResponse result = vault.read("secret/hello");
        assertEquals(result.getData().get("value"), "hello");
        System.out.println("TEST-Read:" + result.toString());
    }

    public void testReadCredentials() throws Exception {
        VaultResponse result = vault.read("postgresql/creds/readonly");
        assertTrue(result.getLeaseId().contains("postgresql/creds/readonly/"));
        assertTrue(result.getRenewable());
        assertEquals(result.getLeaseDuration(), "2592000");
        assertNotNull(result.getData());
        assertNotNull(result.getData().get("password"));
        assertNotNull(result.getData().get("username"));
        assertTrue(result.getData().get("username").contains("root-"));
        System.out.println("Read Credentials:" + result.toString());
    }

    public void testRenewLeaseCredentials() throws Exception {
        VaultResponse result = vault.read("postgresql/creds/readonly");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("increment", "7200");

        VaultResponse renewResponse = vault.renew(result.getLeaseId(), parameters);

        assertEquals(result.getLeaseId(), renewResponse.getLeaseId());
        assertEquals(renewResponse.getLeaseDuration(), "7200");
    }

    public void testRevokeLeaseCredentials() throws Exception {

        VaultResponse result = vault.read("postgresql/creds/readonly");
        vault.revoke(result.getLeaseId());

        try{
            vault.renew(result.getLeaseId(), new HashMap<>());
        } catch (Exception e) {
            assertTrue(e instanceof VaultException);
        }

    }

    public void testDelete() throws Exception {
        vault.delete("secret/hello");
        VaultResponse result = null;
        try {
            result = vault.read("secret/hello");
        } catch (VaultException e) {
            assertEquals(e.getStatusCode(), 404);
        }
    }

    public void testReadWithInvalidToken() throws Exception {
        Vault vault = new Vault(vault_server_url, "invalid");
        VaultResponse result = null;
        try {
            result =  vault.read("secret/hello");
            fail("Expected VaultException");
        } catch (VaultException e) {
            assertEquals(e.getStatusCode(), 403);
        }
    }

    public void testGetStatus() throws Exception {
        VaultStatus vaultStatus = vault.getStatus();

        System.out.println(vaultStatus);

        assertEquals(vaultStatus.getKeyShares(), 1);
        assertEquals(vaultStatus.getKeyThreshold(), 1);
        assertEquals(vaultStatus.getProgress(), 0);
        assertEquals(vaultStatus.isSealed(), false);
    }

    public void testLookupToken() throws Exception {
        assertEquals(vault.lookupToken(this.token).getData().getDisplayName(), "root");
    }

    public void testCreateToken() throws Exception {
        TokenCreateRequest tokenCreateRequest = new TokenCreateRequestBuilder()
                .setDisplayName("foobar")
                .setNumUses(5)
                .setNoParent(true)
                .createTokenRequest();
        TokenResponse createResult = vault.createToken(tokenCreateRequest);
        TokenResponse lookupResult = vault.lookupToken(createResult.getAuth().getClientToken());
        assertEquals(lookupResult.getData().getDisplayName(), "token-foobar");
        assertEquals(lookupResult.getData().getNumUses(), 5);
        assertEquals(lookupResult.getData().getPolicies().get(0), "root");
    }
}