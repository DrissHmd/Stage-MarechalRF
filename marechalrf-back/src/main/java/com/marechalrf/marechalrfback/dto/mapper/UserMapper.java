package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleId")
    UserDto entityToDTO(User model);

    @Mapping(source = "roleId", target = "role.id")
    User dtoToEntity(UserDto model);
}