package com.readingisgood.service;

import com.readingisgood.converter.book.BookDTOConverter;
import com.readingisgood.dao.BookDao;
import com.readingisgood.dto.BookDTO;
import com.readingisgood.model.Book;
import com.readingisgood.request.book.SaveBookRequest;
import com.readingisgood.response.book.SaveBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookDao bookDao;
    private BookDTOConverter converter;

    public BookService(BookDao bookDao, BookDTOConverter converter) {
        this.bookDao = bookDao;
        this.converter = converter;
    }

    public List<BookDTO> findAll() {
        return converter.convert(bookDao.findAll());
    }

    public SaveBookResponse saveBook(SaveBookRequest saveBookRequest) {
        try {
            saveBook(converter.convertSaveBookRequestToBook(saveBookRequest));
        } catch (Exception e) {
            return SaveBookResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        return SaveBookResponse.builder().httpStatus(HttpStatus.CREATED).build();
    }

    private void saveBook(Book book) {
        bookDao.save(book);
    }

    public Optional<BookDTO> findById(Long bookId) {
        Optional<Book> bookOptional = bookDao.findById(bookId);
        return bookOptional.map(book -> converter.convertBookToDTO(book));
    }

    public Optional<Book> findBy(Long bookId) {
        return bookDao.findById(bookId);
    }

    public Optional<Book> findByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }

    public Optional<Book> findByIdWithSkus(Long bookId) {
        return bookDao.findByIdWithSkus(bookId);
    }
}
