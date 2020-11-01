package com.readingisgood.dao;

import com.readingisgood.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);

    @Query("select b from Book b join b.skus s where b.id = :id and b.deleted = 0 and b.status = 'Active'")
    Optional<Book> findByIdWithSkus(@Param("id") Long id);
}
