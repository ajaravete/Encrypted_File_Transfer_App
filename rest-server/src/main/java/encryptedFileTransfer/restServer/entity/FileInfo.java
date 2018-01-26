package encryptedFileTransfer.restServer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileInfo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String sender;
    private String receiver;
    @JsonProperty("file_name")
    private String fileName;

    public FileInfo(int id, String sender, String receiver, String fileName) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.fileName = fileName;
    }

    public FileInfo() {
        this(0, "", "", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
