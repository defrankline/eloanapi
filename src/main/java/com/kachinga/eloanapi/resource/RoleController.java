package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.security.CurrentUser;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {


    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAll(@CurrentUser UserPrincipal currentUser) {
        log.info(currentUser.getUsername() + " requesting roles");
        ApiResponse<?> response = new ApiResponse<>("Roles", roleService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Role role) throws ParseException {
        Role created = roleService.save(role);
        ApiResponse<?> response = new ApiResponse<>("Role Created Successfully", created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
