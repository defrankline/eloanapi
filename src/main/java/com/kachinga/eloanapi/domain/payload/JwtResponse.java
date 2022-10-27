package com.kachinga.eloanapi.domain.payload;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private String jwtToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
