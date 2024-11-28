package fiu.cen.menug.service;

import fiu.cen.menug.model.TwoFACode;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TwoFAService {

    private static final ConcurrentMap<String, TwoFACode> keyStore = new ConcurrentHashMap<String, TwoFACode>();

    public void addKey(TwoFACode twoFACode){

        assert !Objects.isNull(twoFACode);
        keyStore.put(twoFACode.getId(), twoFACode);
    }

    public Optional<TwoFACode> getKeyFor(String id)
    {
        if(keyStore.containsKey(id)){
            return Optional.of(this.keyStore.get(id));
        }

        return Optional.empty();
    }

    public void deleteKeyFor(String id){

        if(keyStore.isEmpty()){
            return;
        }

        keyStore.remove(id);
    }

    public String genKeyFor(String username){

        return String.format("%06d", new Random().nextInt(999999));
    }

    public TwoFACode create2FACodeObj(String username, String code)
    {

        String hashedCode = DigestUtils.md5DigestAsHex(code.getBytes());

        return new TwoFACode(
            username,
            hashedCode,
            LocalDateTime.now().plus(10, ChronoUnit.MINUTES)
        );
   }
}
