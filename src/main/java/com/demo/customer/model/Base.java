package com.demo.customer.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class Base {

    @Column(name = "created_by")
    protected Long createdBy;

    @Column(name = "created_date")
    @CreationTimestamp
    protected Instant createdDate = Instant.now();

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "updated_date")
    @UpdateTimestamp
    protected Instant updatedDate = Instant.now();
}
