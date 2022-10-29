package com.kachinga.eloanapi.service;

import com.kachinga.eloanapi.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    Company findById(Long id);

    Company findByNumber(String number);

    Company save(Company company);

    void delete(Long id);

    List<Company> findAll();

    List<Company> findAll(String query);

    Page<Company> findAll(int page, int size, String sortBy, String direction);

    Page<Company> findAll(String query, int page, int size, String sortBy, String direction);
}
