package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.RoleDto;
import com.marechalrf.marechalrfback.dto.mapper.RoleMapper;
import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.entityToDTOList(roles);
    }

    public Optional<RoleDto> getRoleById(Long id) {
        return roleRepository.findById(id).map(roleMapper::entityToDTO);
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.dtoToEntity(roleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.entityToDTO(savedRole);
    }

    public RoleDto updateRole(Long id, RoleDto roleDetails) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDetails.getName());
        Role updatedRole = roleRepository.save(role);
        return roleMapper.entityToDTO(updatedRole);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
