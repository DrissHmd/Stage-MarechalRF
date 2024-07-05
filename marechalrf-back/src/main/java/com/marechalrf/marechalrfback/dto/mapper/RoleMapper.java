package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.RoleDto;
import com.marechalrf.marechalrfback.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RoleMapper {
    RoleDto entityToDTO(Role model);

    List<RoleDto> entityToDTOList(List<Role> model);

    Role dtoToEntity(RoleDto model);

    List<Role> dtoToEntityList(List<RoleDto> model);
}