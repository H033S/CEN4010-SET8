package fiu.cen.menug.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwoFACode {

    private String id;
    private String value;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
