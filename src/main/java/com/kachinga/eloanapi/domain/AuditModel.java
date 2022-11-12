package com.kachinga.eloanapi.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "active")
    private boolean active = true;
}