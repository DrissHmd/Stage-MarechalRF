package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.RoleUserDto;
import com.marechalrf.marechalrfback.dto.response.Response;
import com.marechalrf.marechalrfback.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/roleuser")
public class RoleUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleUserController.class);

    @Autowired
    private RoleUserService roleUserService;

    @GetMapping
    public ResponseEntity<Response<List<RoleUserDto>>> getAllRoleUsers() {
        Callable<List<RoleUserDto>> f = roleUserService::getAllRoleUsers;
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<RoleUserDto>> getRoleUser(@PathVariable Long id) {
        Callable<RoleUserDto> f = () -> {
            return roleUserService.getRoleUserById(id)
                    .orElseThrow(() -> new RuntimeException("RoleUser not found"));
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @PostMapping
    public ResponseEntity<Response<RoleUserDto>> createRoleUser(@RequestBody RoleUserDto roleUserDto) {
        Callable<RoleUserDto> f = () -> roleUserService.createRoleUser(roleUserDto);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<RoleUserDto>> updateRoleUser(@PathVariable Long id, @RequestBody RoleUserDto roleUserDetails) {
        Callable<RoleUserDto> f = () -> roleUserService.updateRoleUser(id, roleUserDetails);
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteRoleUser(@PathVariable Long id) {
        Callable<Void> f = () -> {
            roleUserService.deleteRoleUser(id);
            return null;
        };
        return ResponseController.createResponseEntity(f, LOGGER, null);
    }
}
