package com.marechalrf.marechalrfback;

import com.marechalrf.marechalrfback.model.Role;
import com.marechalrf.marechalrfback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        addRoleIfNotExists("ROLE_USER");
        addRoleIfNotExists("ROLE_ADMIN");
    }

    private void addRoleIfNotExists(String roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isEmpty()) {
            roleRepository.save(new Role(roleName));
        }
    }
}