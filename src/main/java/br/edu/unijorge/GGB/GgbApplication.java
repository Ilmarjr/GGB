package br.edu.unijorge.GGB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import java.util.HashMap;
//import java.util.Map;
//import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256;

@SpringBootApplication
public class GgbApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgbApplication.class, args);

//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, PBKDF2WithHmacSHA256);
//        encoders.put("pbkdf2", pbkdf2Encoder);
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//
//        String result =  passwordEncoder.encode("ggbadmin");
//        System.out.println("My hash " + result);
    }

}
