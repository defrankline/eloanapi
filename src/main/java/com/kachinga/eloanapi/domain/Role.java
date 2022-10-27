package com.kachinga.eloanapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Role extends AuditModel {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
