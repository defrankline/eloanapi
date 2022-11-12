package com.kachinga.eloanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"number", "company_id"})})
public class Account extends AuditModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private AccountGroup group;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;
}
