package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.RoleDto;
import com.marechalrf.marechalrfback.dto.response.Response;
import com.marechalrf.marechalrfback.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Response<List<RoleDto>>> getAllRoles() {
        Callable<List<RoleDto>> f = roleService::getAllRoles;
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<RoleDto>> getRole(@PathVariable Long id) {
        Callable<RoleDto> f = () -> {
            return roleService.getRoleById(id)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @PostMapping
    public ResponseEntity<Response<RoleDto>> createRole(@RequestBody RoleDto roleDto) {
        Callable<RoleDto> f = () -> roleService.createRole(roleDto);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<RoleDto>> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDetails) {
        Callable<RoleDto> f = () -> roleService.updateRole(id, roleDetails);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteRole(@PathVariable Long id) {
        Callable<Void> f = () -> {
            roleService.deleteRole(id);
            return null;
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }
}
