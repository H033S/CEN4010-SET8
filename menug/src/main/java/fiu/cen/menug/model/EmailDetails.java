package fiu.cen.menug.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;

    @Override
    public String toString() {
        return "EmailDetails{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
