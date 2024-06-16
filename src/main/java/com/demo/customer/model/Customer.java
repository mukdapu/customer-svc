package com.demo.customer.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class Customer extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = -4204488018990828372L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "spending_limit")
    private Double spendingLimit;

    @Column(name = "age")
    private Short age;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Address> address = new ArrayList<>();
}