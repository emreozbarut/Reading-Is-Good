package com.readingisgood.model;

import com.readingisgood.model.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.Active;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Where(clause = "deleted = 0")
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date modifiedDate;
}
