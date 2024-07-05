package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.RoleUserDto;
import com.marechalrf.marechalrfback.model.RoleUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RoleUserMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "role.id", target = "roleId")
    RoleUserDto entityToDTO(RoleUser roleUser);

    List<RoleUserDto> entityToDTOList(List<RoleUser> roleUsers);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "roleId", target = "role.id")
    RoleUser dtoToEntity(RoleUserDto roleUserDto);

    List<RoleUser> dtoToEntityList(List<RoleUserDto> roleUserDtos);
}
