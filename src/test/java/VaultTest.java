import junit.framework.TestCase;

public class VaultTest extends TestCase {

    public void testGetToken() throws Exception {
        Vault vault = new Vault("http://127.0.0.1:8200", "c7e6d2f3-dc1a-a841-cf29-0cf7bec8ed42");
        System.out.println(vault.read("aws/creds/deploy").toString());
    }
}