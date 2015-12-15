import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponseData {

    private List<String> policies;
    private Map<String, String> metadata;
    private String path;
    private String id;

    @JsonProperty("lease_duration")
    private int leaseDuration;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("num_uses")
    private int numUses;

    public String getId() {
        return id;
    }

    public int getNumUses() {
        return numUses;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPath() {
        return path;
    }

    public int getLeaseDuration() {
        return leaseDuration;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "TokenResponseData{" +
                ", leaseDuration=" + leaseDuration +
                ", displayName='" + displayName + '\'' +
                ", numUses=" + numUses +
                ", policies=" + policies +
                ", metadata=" + metadata +
                ", path='" + path + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
