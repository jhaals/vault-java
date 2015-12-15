import com.fasterxml.jackson.annotation.JsonProperty;

public class VaultStatus {
    private boolean sealed;
    private int keyThreshold;
    private int keyShares;
    private int progress;

    public boolean isSealed() {
        return sealed;
    }

    @JsonProperty("t")
    public int getKeyThreshold() {
        return keyThreshold;
    }
    @JsonProperty("n")
    public int getKeyShares() {
        return keyShares;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return "VaultStatus{" +
                "sealed=" + sealed +
                ", keyThreshold=" + keyThreshold +
                ", keyShares=" + keyShares +
                ", progress=" + progress +
                '}';
    }
}