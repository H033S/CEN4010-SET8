package fiu.cen.menug.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class SecurityController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(){}

}
