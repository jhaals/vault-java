import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

public class Vault {
    private final String vaultToken;
    private final Client client;
    private WebTarget baseTarget;

    public Vault(String vaultServer, String vaultToken) {
        this.vaultToken = vaultToken;
        this.client = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class).build();
        this.baseTarget = client.target(vaultServer);
    }
    public Vault(String vaultServer, String vaultToken, Client client) {
        this.vaultToken = vaultToken;
        this.client = client;
        this.baseTarget = client.target(vaultServer);
    }

    public VaultResponse read(String path) {
        WebTarget target = baseTarget.path(String.format("/v1/%s", path));
        Response response = null;
        try {
            response = target.request()
                    .accept("application/json")
                    .header("X-Vault-Token", this.vaultToken)
                    .get(Response.class);
            if (response.getStatus() == 200) {
                return response.readEntity(VaultResponse.class);
            }
            ErrorResponse error = response.readEntity(ErrorResponse.class);
            throw new VaultException(response.getStatus(), error.getErrors());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public void write(String path, HashMap<String, String> secret) {
        WebTarget target = baseTarget.path(String.format("/v1/%s", path));
        Response response = null;
        try {
            response = target.request()
                    .accept("application/json")
                    .header("X-Vault-Token", this.vaultToken)
                    .post(Entity.entity(secret, MediaType.APPLICATION_JSON_TYPE));

            if (response.getStatus() != 204) {
                ErrorResponse error = response.readEntity(ErrorResponse.class);
                throw new VaultException(response.getStatus(), error.getErrors());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public void delete(String path) {
        WebTarget target = baseTarget.path(String.format("/v1/%s", path));
        Response response = null;
        try {
            response = target.request()
                    .accept("application/json")
                    .header("X-Vault-Token", this.vaultToken)
                    .delete();

            if (response.getStatus() != 204) {
                ErrorResponse error = response.readEntity(ErrorResponse.class);
                throw new VaultException(response.getStatus(), error.getErrors());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private static class ErrorResponse {
        @JsonProperty
        private List<String> errors;

        public List<String> getErrors() {
            return errors;
        }
    }

}