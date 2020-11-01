package com.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean deleted;

    @Where(clause = "deleted = 0")
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne
    private Book book;

    @Column
    private Integer quantity;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date modifiedDate;
}
