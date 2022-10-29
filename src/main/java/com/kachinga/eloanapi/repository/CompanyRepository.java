package com.kachinga.eloanapi.repository;


import com.kachinga.eloanapi.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    List<Company> findAllByNameLikeOrNumberLike(String name, String number);

    Page<Company> findAllByNameLikeOrNumberLike(String name, String number, Pageable pageable);

    Optional<Company> findByNumber(String number);
}
