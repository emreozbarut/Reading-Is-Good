package com.readingisgood.model;

import com.readingisgood.model.enums.BaseStatus;
import com.readingisgood.model.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "BOOK")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean deleted;

    @Version
    private int version;

    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.Active;

    @Where(clause = "deleted = 0")
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Sku> skus = new ArrayList<>();

    @Column
    private BigDecimal price;

    @Column
    private String title;

    @Column
    private String author;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date modifiedDate;
}
