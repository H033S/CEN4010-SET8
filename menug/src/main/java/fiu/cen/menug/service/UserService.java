package fiu.cen.menug.service;

import fiu.cen.menug.dto.LoginRequest;

public interface UserService {

    public boolean authenticateUser(LoginRequest loginRequest);
}
