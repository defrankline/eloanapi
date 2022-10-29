package com.kachinga.eloanapi.service.impl;

import com.kachinga.eloanapi.domain.Company;
import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.repository.UserRepository;
import com.kachinga.eloanapi.security.CurrentUser;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.RoleService;
import com.kachinga.eloanapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public User login(String username, String email, String mobile) {
        return repository.findFirstByUsernameOrEmailOrMobile(username, email, mobile).orElse(null);
    }

    @Override
    public User findByNumberAndCompanyId(String number, Long companyId) {
        return repository.findFirstByNumberAndCompanyId(number, companyId).orElse(null);
    }

    @Override
    public User findByMobile(String mobile) {
        return repository.findByMobile(mobile).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public RegisterRequest convertToDto(User user) {
        return modelMapper.map(user, RegisterRequest.class);
    }

    @Override
    public User convertToEntity(RegisterRequest userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public User create(RegisterRequest userDto) {
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword() != null ? user.getPassword() : "Secret1234"));
        return store(userDto, user);
    }

    private User store(RegisterRequest userDto, User user) {
        Set<Role> roles = new HashSet<>();
        if (userDto.getRoles() != null && userDto.getRoles().size() > 0) {
            for (Role role : userDto.getRoles()) {
                Role rw = roleService.findById(role.getId());
                if (rw != null) {
                    roles.add(rw);
                }
            }
        } else {
            Role rw = roleService.findByName("ROLE_USER");
            if (rw != null) {
                roles.add(rw);
            }
        }
        user.setRoles(roles);
        return repository.save(user);
    }


    @Override
    public User update(RegisterRequest userDto) {
        User user = convertToEntity(userDto);
        return store(userDto, user);
    }

    @Override
    public List<User> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    @Override
    public Page<User> findAllByCompanyId(Long companyId, int page, int size, String sortBy, String direction) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            dir = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAllByCompanyId(companyId, pageable);
    }

    public com.kachinga.eloanapi.domain.User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return this.findByUsername(currentUser.getUsername());
    }

    public Company getCurrentUserCompany(@CurrentUser UserPrincipal currentUser) {
        com.kachinga.eloanapi.domain.User user = this.findByUsername(currentUser.getUsername());
        return user.getCompany();
    }
}
