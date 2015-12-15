import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponseAuth {

    private List<String> policies;
    private Map<String, String> meta;
    private int Ttl;
    private boolean renewable;

    @JsonProperty("client_token")
    private String clientToken;

    @JsonProperty("num_uses")
    private boolean noParent;

    @JsonProperty("no_default_profile")
    private boolean noDefaultProfile;

    public String getClientToken() {
        return clientToken;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public boolean isNoParent() {
        return noParent;
    }

    public boolean isNoDefaultProfile() {
        return noDefaultProfile;
    }

    public int getTtl() {
        return Ttl;
    }

    public boolean isRenewable() {
        return renewable;
    }

    @Override
    public String toString() {
        return "TokenResponseAuth{" +
                "policies=" + policies +
                ", meta=" + meta +
                ", Ttl=" + Ttl +
                ", renewable=" + renewable +
                ", clientToken='" + clientToken + '\'' +
                ", noParent=" + noParent +
                ", noDefaultProfile=" + noDefaultProfile +
                '}';
    }
}
