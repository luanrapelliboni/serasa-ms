package br.experian.com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class Base {
    @Id
    @Column(unique = true, updatable = false)
    @Type(type="org.hibernate.type.UUIDCharType")
    protected UUID uuid;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime created;

    @UpdateTimestamp
    protected LocalDateTime modified;

    protected Boolean active;

    protected Boolean deleted;

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID();
        active = Boolean.TRUE;
        deleted = Boolean.FALSE;
    }
}
