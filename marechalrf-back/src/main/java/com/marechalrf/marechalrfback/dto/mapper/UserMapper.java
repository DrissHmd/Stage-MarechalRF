package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.UserDto;
import com.marechalrf.marechalrfback.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UserMapper {
    UserDto entityToDTO(User model);

    List<UserDto> entityToDTOList(List<User> model);

    User dtoToEntity(UserDto model);

    List<User> dtoToEntityList(List<UserDto> model);
}

