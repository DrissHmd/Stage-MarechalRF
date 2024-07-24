package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UserMapper {
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    UserDto entityToDTO(User model);

    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    User dtoToEntity(UserDto model);
}