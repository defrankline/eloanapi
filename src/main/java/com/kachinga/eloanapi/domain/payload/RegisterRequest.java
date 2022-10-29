package com.kachinga.eloanapi.domain.payload;

import com.kachinga.eloanapi.domain.Company;
import com.kachinga.eloanapi.domain.Role;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest implements Serializable {
    private Long id;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "mobile is required")
    private String mobile;

    @NotNull(message = "password is required")
    private String password;

    private String number;

    @NotNull(message = "company is required")
    private Company company;

    @NotNull(message = "Role is required")
    private Set<Role> roles;
}
