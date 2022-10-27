package com.kachinga.eloanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Company extends AuditModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", unique = true)
    private String number;
}
