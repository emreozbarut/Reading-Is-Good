package com.readingisgood.controller;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.model.enums.Genre;
import com.readingisgood.request.book.SaveBookRequest;
import com.readingisgood.response.book.SaveBookResponse;
import com.readingisgood.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    public void shouldFindAllBooks() {
        BookDTO bookDTO1 = BookDTO.builder().author("author1").genre(Genre.ADVENTURE).price(BigDecimal.valueOf(10)).build();
        BookDTO bookDTO2 = BookDTO.builder().author("author2").genre(Genre.FANTASY).price(BigDecimal.valueOf(20)).build();
        BookDTO bookDTO3 = BookDTO.builder().author("author3").genre(Genre.ROMANCE).price(BigDecimal.valueOf(30)).build();

        List<BookDTO> bookDTOList = List.of(bookDTO1, bookDTO2, bookDTO3);

        when(bookService.findAll()).thenReturn(bookDTOList);

        List<BookDTO> result = bookController.findAll();

        assertEquals(bookDTOList, result);
    }

    @Test
    public void shouldSaveBook() {
        SaveBookRequest saveBookRequest = new SaveBookRequest();
        SaveBookResponse saveBookResponse = SaveBookResponse.builder().httpStatus(HttpStatus.CREATED).build();

        when(bookService.saveBook(saveBookRequest)).thenReturn(saveBookResponse);

        SaveBookResponse response = bookController.save(saveBookRequest);

        assertEquals(saveBookResponse.getHttpStatus(), response.getHttpStatus());
    }

}