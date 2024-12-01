package fiu.cen.menug.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<?> welcomeMessage(Principal principal){
        return ResponseEntity.ok("Hello World " + principal.getName());
    }
}
