package fiu.cen.menug.controller;

import fiu.cen.menug.model.EmailDetails;
import fiu.cen.menug.model.TwoFACode;
import fiu.cen.menug.model.User;
import fiu.cen.menug.service.EmailService;
import fiu.cen.menug.service.TokenService;
import fiu.cen.menug.service.TwoFAService;
import fiu.cen.menug.service.CustomeUserDetailsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final CustomeUserDetailsService userDetailsService;
    private final TwoFAService twoFAService;
    private final EmailService emailService;
    private final TokenService tokenService;

    public AuthController(CustomeUserDetailsService userDetailsService, TwoFAService twoFAService, EmailService emailService, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.twoFAService = twoFAService;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/2fa-code")
    public ResponseEntity<TwoFACode> gen2FACode(Authentication authentication) {

        LOG.info("Generating 2FA Code for User: {}", authentication.getName());
        final String code = twoFAService.genKeyFor(authentication.getName());
        final TwoFACode codeObj = twoFAService.create2FACodeObj(authentication.getName(), code);
        twoFAService.addKey(codeObj);

        LOG.info("Getting Email for username: {}", authentication.getName());
        final User userInst = userDetailsService.loadUserByUsername(authentication.getName());
        final String email = userInst.getEmail();

        LOG.info("Notifying user with code to email {}", email);
        final EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setMsgBody("Code: " + code);
        details.setSubject("RMG: 2 FA Code");

        emailService.sendSimpleEmail(details);
        return ResponseEntity.ok(codeObj);
    }

    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/token")
    public String token(Authentication authentication) {

        LOG.info("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);

        LOG.info("Token granted {}", token);
        return token;
    }
}
