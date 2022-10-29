package com.kachinga.eloanapi.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Frank Kachinga <frank.kachinga@gmail.com>
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "account_groups")
public class AccountGroup extends AuditModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "sub_type_id", nullable = false)
    private AccountSubType subType;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private BalanceNature nature;
}
