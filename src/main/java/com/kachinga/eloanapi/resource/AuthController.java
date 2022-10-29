package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.domain.payload.JwtResponse;
import com.kachinga.eloanapi.domain.payload.LoginRequest;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.security.JwtUtils;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.UserService;
import com.kachinga.eloanapi.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final UserService userService;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpiration;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Date expiration = new Date((new Date()).getTime() + jwtExpiration);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication, expiration);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwtToken, expiration,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getCompany(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) throws ParseException {
        String password = registerRequest.getPassword();
        String username = registerRequest.getUsername();
        String number = registerRequest.getNumber() != null ? registerRequest.getNumber() : RandomUtil.generateContractNumber();
        User row = userService.findByNumberAndCompanyId(registerRequest.getNumber(), registerRequest.getCompany().getId());
        if (row != null) {
            ApiResponse<?> response = new ApiResponse<>("User already exist!", registerRequest);
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (registerRequest.getPassword() == null) {
            password = "Secret1234";
        }
        if (registerRequest.getUsername() == null) {
            username = number;
        }
        registerRequest.setPassword(password);
        registerRequest.setUsername(username);
        registerRequest.setNumber(number);

        User user = userService.create(registerRequest);
        ApiResponse<?> response = new ApiResponse<>("User Created Successfully", user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
