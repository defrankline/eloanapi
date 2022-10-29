package com.kachinga.eloanapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stock_categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StockCategory extends AuditModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private StockType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debit_account_id", nullable = false)
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_account_id", nullable = false)
    private Account creditAccount;

    @Column(name = "collateral", nullable = false)
    private boolean collateral = false;
}
