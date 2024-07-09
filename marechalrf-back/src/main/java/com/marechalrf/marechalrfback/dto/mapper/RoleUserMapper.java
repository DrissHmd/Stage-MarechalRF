package com.marechalrf.marechalrfback.dto.mapper;

import com.marechalrf.marechalrfback.dto.RoleUserDto;
import com.marechalrf.marechalrfback.model.RoleUser;
import com.marechalrf.marechalrfback.model.UserRoleKey;
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

    default UserRoleKey map(Long userId, Long roleId) {
        return new UserRoleKey(userId, roleId);
    }

    default Long mapUserRoleKeyToUserId(UserRoleKey value) {
        return value != null ? value.getUserId() : null;
    }

    default UserRoleKey mapToUserRoleKey(Long userId, Long roleId) {
        return new UserRoleKey(userId, roleId);
    }

    default UserRoleKey map(Long id) {
        return new UserRoleKey(id, null); // Adjust as necessary
    }
}
