package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.RoleUserDto;
import com.marechalrf.marechalrfback.dto.mapper.RoleUserMapper;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.model.RoleUser;
import com.marechalrf.marechalrfback.model.User;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import com.marechalrf.marechalrfback.repository.RoleUserRepository;
import com.marechalrf.marechalrfback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUserService {

    private final RoleUserMapper roleUserMapper;
    private final RoleUserRepository roleUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public RoleUserService(RoleUserMapper roleUserMapper, RoleUserRepository roleUserRepository) {
        this.roleUserMapper = roleUserMapper;
        this.roleUserRepository = roleUserRepository;
    }

    public List<RoleUserDto> getAllRoleUsers() {
        List<RoleUser> roleUsers = roleUserRepository.findAll();
        return roleUserMapper.entityToDTOList(roleUsers);
    }

    public Optional<RoleUserDto> getRoleUserById(Long id) {
        return roleUserRepository.findById(id).map(roleUserMapper::entityToDTO);
    }

    public RoleUserDto createRoleUser(RoleUserDto roleUserDto) {
        RoleUser roleUser = roleUserMapper.dtoToEntity(roleUserDto);
        RoleUser savedRoleUser = roleUserRepository.save(roleUser);
        return roleUserMapper.entityToDTO(savedRoleUser);
    }

    public RoleUserDto updateRoleUser(Long id, RoleUserDto roleUserDetails) {
        RoleUser roleUser = roleUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoleUser not found"));

        User user = userRepository.findById(roleUserDetails.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleUserDetails.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        roleUser.setUser(user);
        roleUser.setRole(role);

        RoleUser updatedRoleUser = roleUserRepository.save(roleUser);
        return roleUserMapper.entityToDTO(updatedRoleUser);
    }

    public void deleteRoleUser(Long id) {
        roleUserRepository.deleteById(id);
    }
}
