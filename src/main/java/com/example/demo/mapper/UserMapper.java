package com.example.demo.mapper;

import com.example.demo.entity.Phone;
import com.example.demo.entity.User;
import com.example.demo.model.UserResponse;
import com.example.demo.model.UsersPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "modified", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "active", constant = "true")
    User toEntity(UsersPostRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true) // Si `Phone` tiene una relación con `User`
    Phone phone(com.example.demo.model.Phone request);

    @Mapping(target = "id", source = "id", qualifiedByName = "longToUuid")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "modified", source = "modified")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "token", source = "token")
    @Mapping(target = "isactive", source = "active")
    UserResponse toResponse(User user);

    @Named("longToUuid")
    default UUID longToUuid(Long value) {
        return value != null ? new UUID(0L, value) : null;
    }

}