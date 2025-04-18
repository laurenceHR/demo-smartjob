package com.example.demo.service;

import com.example.demo.config.ParamsConfiguration;
import com.example.demo.entity.Phone;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper mapper;

    private ParamsConfiguration params;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper mapper,
            ParamsConfiguration params) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.params = params;
    }

    @Override
    public void saveUser(UsersPostRequest request) {
        Optional<User> optUser = userRepository.findByEmail(request.getEmail());
        if (optUser.isPresent()) {
            throw new UserException("El correo ya se ha registrado");
        }
        User userEntity = mapper.toEntity(request);
        userEntity.getPhones().forEach(phone -> {
            log.info("Phone: {}", phone);
            phone.setUser(userEntity);
        });
        userRepository.save(userEntity);
        System.out.println("User saved: " + request);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));
        UserResponse userResponse = mapper.toResponse(user);
        userResponse.setId(UUID.randomUUID());
        String jwt = Jwts.builder()
                .setSubject(userResponse.getId().toString())
                .claim("email", user.getEmail())
                .claim("isactive", user.isActive())
                .signWith(SignatureAlgorithm.HS256, params.getJwtSecretKey())
                .compact();
        userResponse.setToken(jwt);
        return userResponse;
    }
}
