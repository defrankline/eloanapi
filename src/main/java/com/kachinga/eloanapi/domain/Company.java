package com.kachinga.eloanapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Company extends AuditModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", unique = true)
    private String number;
}
