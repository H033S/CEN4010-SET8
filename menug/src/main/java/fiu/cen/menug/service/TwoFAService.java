package fiu.cen.menug.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import fiu.cen.menug.model.TwoFACode;

@Service
public class TwoFAService {

    public String genKeyFor(String username) {

        return String.format("%06d", new Random().nextInt(999999));
    }

    public TwoFACode create2FACodeObj(String username, String code) {

        final LocalDateTime expiryDate = LocalDateTime.now().plus(10, ChronoUnit.MINUTES);
        final String hashedCode = DigestUtils.md5DigestAsHex(code.getBytes());

        return new TwoFACode(
                username,
                hashedCode,
                expiryDate);

    }
}
