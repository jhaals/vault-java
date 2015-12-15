import java.util.List;
import java.util.Map;

public class TokenCreateRequestBuilder {
    private String id;
    private String displayName;
    private List<String> policies;
    private Map<String, String> meta;
    private Boolean noParent;
    private Boolean noDefaultProfile;
    private int ttl;
    private int numUses;

    public TokenCreateRequestBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TokenCreateRequestBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public TokenCreateRequestBuilder setPolicies(List<String> policies) {
        this.policies = policies;
        return this;
    }

    public TokenCreateRequestBuilder setMeta(Map<String, String> meta) {
        this.meta = meta;
        return this;
    }

    public TokenCreateRequestBuilder setNoParent(Boolean noParent) {
        this.noParent = noParent;
        return this;
    }

    public TokenCreateRequestBuilder setNoDefaultProfile(Boolean noDefaultProfile) {
        this.noDefaultProfile = noDefaultProfile;
        return this;
    }

    public TokenCreateRequestBuilder setTtl(int ttl) {
        this.ttl = ttl;
        return this;
    }

    public TokenCreateRequestBuilder setNumUses(int numUses) {
        this.numUses = numUses;
        return this;
    }

    public TokenCreateRequest createTokenRequest() {
        return new TokenCreateRequest(id, displayName, policies, meta, noParent, noDefaultProfile, ttl, numUses);
    }
}