package com.kachinga.eloanapi.resource;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.ApiResponse;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.messaging.producer.UserUploadProducer;
import com.kachinga.eloanapi.security.CurrentUser;
import com.kachinga.eloanapi.security.UserPrincipal;
import com.kachinga.eloanapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUploadProducer userUploadProducer;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "20") int size,
                                    @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                    @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection,
                                    @CurrentUser UserPrincipal currentUser) {
        Page<User> users = userService.findAllByCompanyId(currentUser.getCompany().getId(), page, size, sortBy, sortDirection);
        ApiResponse<?> response = new ApiResponse<>("Users", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody RegisterRequest registerRequest,
                                    @CurrentUser UserPrincipal currentUser) {
        registerRequest.setId(id);
        registerRequest.setCompany(currentUser.getCompany());
        userUploadProducer.produce(registerRequest);
        ApiResponse<?> response = new ApiResponse<>("Users are being uploaded you will notified soon");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody List<RegisterRequest> registerRequests, @CurrentUser UserPrincipal currentUser) {
        if (registerRequests.size() > 0) {
            for (RegisterRequest registerRequest : registerRequests) {
                registerRequest.setId(null);
                registerRequest.setCompany(currentUser.getCompany());
                registerRequest.setPassword("Secret1234");
                registerRequest.setUsername(registerRequest.getEmail());
                userUploadProducer.produce(registerRequest);
            }
            ApiResponse<?> response = new ApiResponse<>("Users are being uploaded you will notified soon");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<?> response = new ApiResponse<>("Not user to upload", registerRequests);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
