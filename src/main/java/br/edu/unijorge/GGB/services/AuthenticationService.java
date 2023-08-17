package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.DTOs.security.AccountCredentialsDTO;
import br.edu.unijorge.GGB.DTOs.security.TokenDTO;
import br.edu.unijorge.GGB.repositories.UserRepository;
import br.edu.unijorge.GGB.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signIn(AccountCredentialsDTO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);

        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
