package com.kachinga.eloanapi.service;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;

import java.text.ParseException;
import java.util.Optional;

public interface UserService {
    User login(String username, String email, String mobile);

    User findByNumberAndCompanyId(String number, Long companyId);

    User create(RegisterRequest userDto) throws ParseException;

    User update(RegisterRequest userDto) throws ParseException;

    RegisterRequest convertToDto(User user);

    User convertToEntity(RegisterRequest userDto) throws ParseException;
}
