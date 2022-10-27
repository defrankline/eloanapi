package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.Role;
import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.domain.payload.JwtResponse;
import com.kachinga.eloanapi.domain.payload.LoginRequest;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.security.JwtUtils;
import com.kachinga.eloanapi.security.UserDetailsImpl;
import com.kachinga.eloanapi.service.RoleService;
import com.kachinga.eloanapi.service.UserService;
import com.kachinga.eloanapi.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {


    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAll() {
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
