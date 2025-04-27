package com.codigo04.uploadassets.service;

import com.codigo04.uploadassets.models.User;
import com.codigo04.uploadassets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.assets.default.user}")
    private String username;

    @Value("${app.assets.default.pass}")
    private String password;

    @Value("${app.assets.default.email}")
    private String email;

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.findByUsername(username).isPresent()) return;

        User deafultUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        userRepository.save(deafultUser);
    }
}
