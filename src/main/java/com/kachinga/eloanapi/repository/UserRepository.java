package com.kachinga.eloanapi.repository;

import com.kachinga.eloanapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsernameOrEmailOrMobile(String username, String email, String mobile);

    Optional<User> findByUsername(String username);

    Optional<User> findByCompanyIdAndUsername(Long companyId, String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByCompanyIdAndEmail(Long companyId, String email);

    Optional<User> findByMobile(String mobile);
    Optional<User> findByCompanyIdAndMobile(Long companyId, String mobile);

    Optional<User> findFirstByNumberAndCompanyId(String number, Long companyId);

    List<User> findAllByCompanyId(Long companyId);

    Page<User> findAllByCompanyId(Long companyId, Pageable pageable);
}
