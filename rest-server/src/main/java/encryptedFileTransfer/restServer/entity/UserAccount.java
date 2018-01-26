package encryptedFileTransfer.restServer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccount {
    private String email;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty("public_key")
    private String publicKey;

    public UserAccount(String email, String name, String password, String publicKey) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.publicKey = publicKey;
    }

    public UserAccount() {
        this("", "", "", "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
