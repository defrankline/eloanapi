package com.kachinga.eloanapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transaction_items")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "balance_nature")
    private BalanceNature balanceNature;
}
