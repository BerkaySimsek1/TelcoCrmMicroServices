package com.etiya.authservice;

import com.etiya.common.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSecurity
public class AuthServiceApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {

        SpringApplication.run(AuthServiceApplication.class, args);

        /*
       KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
       keyGenerator.init(256);
       SecretKey secretKey = keyGenerator.generateKey();
       String base64 =  Base64.getEncoder().encodeToString(secretKey.getEncoded());
       System.out.println(base64);

         */

	}


}
