package br.edu.unijorge.GGB.controllers;

import br.edu.unijorge.GGB.DTOs.security.AccountCredentialsDTO;
import br.edu.unijorge.GGB.services.AuthenticationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
