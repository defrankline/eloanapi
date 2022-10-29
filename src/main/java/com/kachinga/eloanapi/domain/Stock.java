package com.kachinga.eloanapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kachinga.eloanapi.util.Util;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Stock extends AuditModel {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private StockCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debit_account_id", nullable = false)
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_account_id", nullable = false)
    private Account creditAccount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "time")
    private LocalDateTime time = LocalDateTime.now();

    @Column(name = "balance", nullable = false)
    private BigDecimal amount;

    @Column(name = "receipt")
    private String receipt;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "reference_number")
    private String referenceNumber = Util.randomNumeric(32);

    @Column(name = "verified")
    private boolean verified = false;

    @Column(name = "deposit")
    private boolean deposit = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verified_by")
    private User verifiedBy;
}
