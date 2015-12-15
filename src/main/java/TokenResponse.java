import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {

    private Boolean renewable;
    private TokenResponseData data;

    @JsonProperty("auth")
    private TokenResponseAuth auth;

    @JsonProperty("lease_id")
    private String leaseId;

    @JsonProperty("lease_duration")
    private String leaseDuration;

    public TokenResponseAuth getAuth() {
        return auth;
    }

    public TokenResponseData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "data=" + data +
                ", leaseId='" + leaseId + '\'' +
                ", leaseDuration='" + leaseDuration + '\'' +
                ", renewable=" + renewable +
                ", auth=" + auth +
                '}';
    }
}
