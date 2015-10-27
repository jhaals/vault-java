import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VaultResponse {
    /*
        {"lease_id":"","renewable":false,"lease_duration":2592000,"data":{"value":"world"},"auth":null}
    */
    @JsonProperty("lease_id")
    private String leaseId;
    @JsonProperty
    private Boolean renewable;
    @JsonProperty("lease_duration")
    private String leaseDuration;
    @JsonProperty
    private Map<String, String> data;

    public String getLeaseId() {
        return leaseId;
    }

    public Boolean getRenewable() {
        return renewable;
    }

    public String getLeaseDuration() {
        return leaseDuration;
    }

    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "VaultResponse{" +
                "leaseId='" + leaseId + '\'' +
                ", renewable=" + renewable +
                ", leaseDuration='" + leaseDuration + '\'' +
                ", data=" + data +
                '}';
    }
}
