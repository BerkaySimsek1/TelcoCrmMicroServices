package com.etiya.authservice.service.abstracts;

import com.etiya.authservice.service.dtos.RegisterUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.AccessDeniedException;

public interface UserService extends UserDetailsService {

    void add(RegisterUserRequest request);

}
