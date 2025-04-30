package com.codigo04.uploadassets;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilsTest {

    @Test
    public void generateBcryptTest(){
        String string = "dev";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(string);

        System.out.println("-> " + encodePassword + " <-");
    }

}
