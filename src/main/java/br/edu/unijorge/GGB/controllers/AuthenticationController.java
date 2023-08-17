package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.DTOs.security.AccountCredentialsDTO;
import br.edu.unijorge.GGB.services.AuthenticationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsDTO data){
        if(data == null || StringUtils.isBlank(data.getUsername()) || StringUtils.isBlank(data.getPassword()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = authenticationService.signIn(data);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }
    @SuppressWarnings("rawtypes")
    @PutMapping("/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = authenticationService.refreshToken(username, refreshToken);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }
}
