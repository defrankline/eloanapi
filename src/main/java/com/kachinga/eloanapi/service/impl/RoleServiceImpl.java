package com.kachinga.eloanapi.service.impl;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.repository.RoleRepository;
import com.kachinga.eloanapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElse(null);
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
}
