package br.edu.unijorge.GGB.services;

import br.edu.unijorge.GGB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository repository){
        userRepository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if ( user != null) {
            return user;
        }else{
            throw  new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
