package com.kachinga.eloanapi.service.impl;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.repository.UserRepository;
import com.kachinga.eloanapi.service.RoleService;
import com.kachinga.eloanapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
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
    public RegisterRequest convertToDto(User user) {
        return modelMapper.map(user, RegisterRequest.class);
    }

    @Override
    public User convertToEntity(RegisterRequest userDto) throws ParseException {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public User create(RegisterRequest userDto) throws ParseException {
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return store(userDto, user);
    }

    private User store(RegisterRequest userDto, User user) {
        Set<Role> roles = new HashSet<>();
        if (userDto.getRoles().size() > 0) {
            for (Role role : userDto.getRoles()) {
                Role rw = roleService.findById(role.getId());
                if (rw != null) {
                    roles.add(rw);
                }
            }
        }
        user.setRoles(roles);
        return repository.save(user);
    }

    @Override
    public User update(RegisterRequest userDto) throws ParseException {
        User user = convertToEntity(userDto);
        return store(userDto, user);
    }
}
