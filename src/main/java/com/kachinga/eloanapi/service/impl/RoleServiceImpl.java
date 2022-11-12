package com.kachinga.eloanapi.service.impl;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.repository.RoleRepository;
import com.kachinga.eloanapi.service.RoleService;
import com.kachinga.eloanapi.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Role findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElse(null);
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> update(UUID uuid, Role role) {
        log.info("updating role {}", role);
        Role found = this.findByUuid(uuid);
        if (found == null) {
            return Util.respond(role, "Role not found", HttpStatus.NOT_FOUND);
        }
        return Util.respond(this.save(role), "Role updated successfully", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> destroy(UUID uuid) {
        log.info("deleting role {}", uuid);
        Role found = this.findByUuid(uuid);
        if (found == null) {
            return Util.respond(uuid, "Role not found", HttpStatus.NOT_FOUND);
        }
        delete(found.getId());
        return Util.respond("Role deleted successfully", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> create(Role role) {
        log.info("creating role {}", role);
        return Util.respond(this.save(role), "Role created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAll() {
        log.info("fetching roles");
        return Util.respond(this.findAll(), "Roles", HttpStatus.OK);
    }
}
