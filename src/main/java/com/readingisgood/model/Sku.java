package com.readingisgood.model;

import com.readingisgood.model.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "SKU")
@NoArgsConstructor
@AllArgsConstructor
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.Active;

    @Basic
    private Integer stock = 1;

    @Where(clause = "deleted = 0")
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Book book;
}
