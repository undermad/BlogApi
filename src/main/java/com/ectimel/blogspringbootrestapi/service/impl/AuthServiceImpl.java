package com.ectimel.blogspringbootrestapi.service.impl;

import com.ectimel.blogspringbootrestapi.entity.Role;
import com.ectimel.blogspringbootrestapi.entity.User;
import com.ectimel.blogspringbootrestapi.exception.BlogAPIException;
import com.ectimel.blogspringbootrestapi.payload.LoginDto;
import com.ectimel.blogspringbootrestapi.payload.RegisterDto;
import com.ectimel.blogspringbootrestapi.repository.RoleRepository;
import com.ectimel.blogspringbootrestapi.repository.UserRepository;
import com.ectimel.blogspringbootrestapi.security.JwtTokenProvider;
import com.ectimel.blogspringbootrestapi.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        //check if username exist in database
        if (userRepository.findByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail()).isPresent()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username or email already exist.");
        }

        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> userRoles = new HashSet<>();
        Role userRole = null;
        if (roleRepository.findByName("ROLE_USER").isPresent()) {
            userRole = roleRepository.findByName("ROLE_USER").get();
        }
        userRoles.add(userRole);
        user.setRoles(userRoles);

        userRepository.save(user);
        return "Registered successfully.";
    }
}
