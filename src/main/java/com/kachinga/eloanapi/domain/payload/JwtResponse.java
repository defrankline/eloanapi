package com.kachinga.eloanapi.domain.payload;

import com.kachinga.eloanapi.domain.Company;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private String accessToken;
    private Date tokenExpiration;
    private Long id;
    private String username;
    private String email;
    private Company company;
    private List<String> roles;
}
