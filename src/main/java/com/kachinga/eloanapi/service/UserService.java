package com.kachinga.eloanapi.service;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User login(String username, String email, String mobile);

    User findByNumberAndCompanyId(String number, Long companyId);

    User findByMobile(String mobile);

    User findByUsername(String username);

    User findByEmail(String email);

    User create(RegisterRequest userDto) throws ParseException;

    User update(RegisterRequest userDto) throws ParseException;

    RegisterRequest convertToDto(User user);

    User convertToEntity(RegisterRequest userDto) throws ParseException;

    List<User> findAllByCompanyId(Long companyId);

    Page<User> findAllByCompanyId(Long companyId, int page, int size, String sortBy, String direction);
}
