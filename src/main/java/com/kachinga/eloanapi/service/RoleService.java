package com.kachinga.eloanapi.service;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface RoleService {
    Role findById(Long id);

    Role findByName(String name);

    Role save(Role role);

    void delete(Long id);

    List<Role> findAll();

    Page<Role> findAll(Pageable pageable);
}
