package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.Company;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.security.CurrentUser;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "0") int size,
                                    @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                    @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection,
                                    @RequestParam(name = "query", defaultValue = "") String query) {
        if (size < 0) {
            size = 0;
        }
        if (page < 0) {
            page = 0;
        }

        if (size == 0) {
            List<Company> items = companyService.findAll();
            if (!query.isEmpty()) {
                items = companyService.findAll(query);
            }
            ApiResponse<?> response = new ApiResponse<>("Companies", items);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Page<Company> items = companyService.findAll(page, size, sortBy, sortDirection);
            if (!query.isEmpty()) {
                items = companyService.findAll(query, page, size, sortBy, sortDirection);
            }
            ApiResponse<?> response = new ApiResponse<>("Companies", items);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Company company) {
        Company created = companyService.save(company);
        ApiResponse<?> response = new ApiResponse<>("Company Created Successfully", created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Company company) {
        Company optionalCompany = companyService.findById(id);
        if (optionalCompany == null) {
            ApiResponse<?> response = new ApiResponse<>("Company not found", company);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Company updated = companyService.save(company);
        ApiResponse<?> response = new ApiResponse<>("Company Updated Successfully", updated);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Company optionalCompany = companyService.findById(id);
        if (optionalCompany == null) {
            ApiResponse<?> response = new ApiResponse<>("Company not found", id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        ApiResponse<?> response = new ApiResponse<>("Company", optionalCompany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Company optionalCompany = companyService.findById(id);
        if (optionalCompany == null) {
            ApiResponse<?> response = new ApiResponse<>("Company not found", id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        companyService.delete(id);
        ApiResponse<?> response = new ApiResponse<>("Company Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
