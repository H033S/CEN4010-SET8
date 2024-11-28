package fiu.cen.menug.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TwoFACode {

    private String id;
    private String value;
    private LocalDateTime expiryAt;

    public TwoFACode(String id, String value, LocalDateTime expiryAt) {
        this.id = id;
        this.value = value;
        this.expiryAt = expiryAt;
    }

    @Override
    public String toString() {
        return "TwoFACode{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", expiryAt=" + expiryAt +
                '}';
    }
}
