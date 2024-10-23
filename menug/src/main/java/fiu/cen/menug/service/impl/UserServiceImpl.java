package fiu.cen.menug.service.impl;

import fiu.cen.menug.dto.LoginRequest;
import fiu.cen.menug.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean authenticateUser(LoginRequest loginRequest) {
        return false;
    }
}
