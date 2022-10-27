package com.kachinga.eloanapi.domain.payload;

import com.kachinga.eloanapi.domain.AuditModel;
import com.kachinga.eloanapi.domain.Company;
import com.kachinga.eloanapi.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
