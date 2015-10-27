import java.util.List;

public class VaultException extends RuntimeException {
    private final int statusCode;
    private final List<String> messages;

    public VaultException(int statusCode, List<String> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }
}