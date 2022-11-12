package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.security.CurrentUser;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.RoleService;
import com.kachinga.eloanapi.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Role role) {
        return roleService.create(role);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return roleService.getAll();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid, @Valid @RequestBody Role role) {
        return roleService.update(uuid, role);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        return roleService.destroy(uuid);
    }
}
