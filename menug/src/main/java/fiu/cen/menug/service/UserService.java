package fiu.cen.menug.service;

import fiu.cen.menug.dto.LoginRequest;

public interface UserService {

    boolean authenticateUser(LoginRequest loginRequest);
}
