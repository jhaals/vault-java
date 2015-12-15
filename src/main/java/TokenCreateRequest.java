import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenCreateRequest {
    private String id;
    private List<String> policies;
    private Map<String, String> meta;
    private int Ttl;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("no_parent")
    private Boolean noParent;

    @JsonProperty("no_default_profile")
    private Boolean noDefaultProfile;

    @JsonProperty("num_uses")
    private int numUses;

    /**
     * Create new vault token. It's recommended to use the TokenCreateRequestBuilder
     * @param id The ID of the client token. Can only be specified by a root token. Otherwise, the token ID is a randomly generated UUID.
     * @param displayName The display name of the token. Defaults to "token".
     * @param policies A list of policies for the token. This must be a subset of the policies belonging to the token making the request, unless root. If not specified, defaults to all the policies of the calling token.
     * @param meta A map of string to string valued metadata. This is passed through to the audit backends.
     * @param noParent If true and set by a root caller, the token will not have the parent token of the caller. This creates a token with no parent.
     * @param noDefaultProfile  If true the default profile will not be a part of this token's policy set.
     * @param ttl The TTL period of the token, provided as "1h", where hour is the largest suffix. If not provided, the token is valid for the default lease TTL, or indefinitely if the root policy is used.
     * @param numUses The maximum uses for the given token. This can be used to create a one-time-token or limited use token. Defaults to 0, which has no limit to number of uses.
     */
    public TokenCreateRequest(String id, String displayName, List<String> policies, Map<String, String> meta, Boolean noParent, Boolean noDefaultProfile, int ttl, int numUses) {
        this.id = id;
        this.displayName = displayName;
        this.policies = policies;
        this.meta = meta;
        this.noParent = noParent;
        this.noDefaultProfile = noDefaultProfile;
        Ttl = ttl;
        this.numUses = numUses;
    }

}
