package com.relaxcoder.noticesapi.service.impl;


import com.relaxcoder.noticesapi.dtos.LoginDto;
import com.relaxcoder.noticesapi.dtos.RegisterDto;
import com.relaxcoder.noticesapi.entity.Role;
import com.relaxcoder.noticesapi.entity.User;
import com.relaxcoder.noticesapi.exception.CustomException;
import com.relaxcoder.noticesapi.repository.RoleRepository;
import com.relaxcoder.noticesapi.repository.UserRepository;
import com.relaxcoder.noticesapi.security.JwtTokenProvider;
import com.relaxcoder.noticesapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String registerUser(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Username already exist");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Email Already Exits");
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        User user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(registerDto.getPassword())
                .roles(roles)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";

    }
}
