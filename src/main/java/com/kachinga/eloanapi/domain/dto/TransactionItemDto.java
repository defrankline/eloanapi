package com.kachinga.eloanapi.domain.dto;

import com.kachinga.eloanapi.domain.Account;
import com.kachinga.eloanapi.domain.BalanceNature;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionItemDto {
    private Account account;
    private BalanceNature nature;
    private BigDecimal amount;
}
