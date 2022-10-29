package com.kachinga.eloanapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "account_balances")
public class AccountBalance extends AuditModel {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "time")
    private LocalDateTime time = LocalDateTime.now();

    @Column(name = "balance",nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}