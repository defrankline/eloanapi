package com.kachinga.eloanapi.service.impl;

import com.kachinga.eloanapi.domain.Company;
import com.kachinga.eloanapi.repository.CompanyRepository;
import com.kachinga.eloanapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

    public Company findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Company findByNumber(String name) {
        return repository.findByNumber(name).orElse(null);
    }

    @Override
    public Company save(Company role) {
        return repository.save(role);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Company> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Company> findAll(String query) {
        return repository.findAllByNameLikeOrNumberLike(query.toLowerCase(Locale.ENGLISH), query.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Page<Company> findAll(int page, int size, String sortBy, String direction) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            dir = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable);
    }

    @Override
    public Page<Company> findAll(String query, int page, int size, String sortBy, String direction) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.equals("DESC")) {
            dir = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAllByNameLikeOrNumberLike(query.toLowerCase(Locale.ENGLISH), query.toLowerCase(Locale.ENGLISH), pageable);
    }
}
