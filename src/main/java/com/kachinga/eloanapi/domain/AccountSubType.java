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
@Table(name = "account_sub_types")
public class AccountSubType extends AuditModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private AccountType type;
}
