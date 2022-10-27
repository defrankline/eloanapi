package com.kachinga.eloanapi.repository;

import com.kachinga.eloanapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsernameOrEmailOrMobile(String username,String email,String mobile);

    Optional<User> findFirstByNumberAndCompanyId(String number, Long companyId);
}
