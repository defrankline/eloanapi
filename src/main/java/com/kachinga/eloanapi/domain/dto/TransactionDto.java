package com.kachinga.eloanapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kachinga.eloanapi.domain.TransactionItem;
import com.kachinga.eloanapi.domain.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransactionDto {
    @NotNull
    private String narration;

    private String referenceNumber;

    private String chequeNumber;

    private String payee;

    private Boolean effect;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "transactionDateTime", nullable = false)
    private LocalDateTime transactionDateTime = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "transaction")
    @Fetch(FetchMode.JOIN)
    private List<TransactionItem> transactionItems;

    private TransactionType transactionType;
}
