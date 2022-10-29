package com.kachinga.eloanapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock_types")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StockType extends AuditModel {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
