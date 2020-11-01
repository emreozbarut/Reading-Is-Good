package com.readingisgood.converter.book;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.dto.SkuDTO;
import com.readingisgood.model.Book;
import com.readingisgood.model.Sku;
import com.readingisgood.model.enums.BaseStatus;
import com.readingisgood.model.enums.Genre;
import com.readingisgood.request.book.SaveBookRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDTOConverter {

    public List<BookDTO> convert(List<Book> books) {
        return books.stream().map(this::convertBookToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO convertBookToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .status(book.getStatus().name())
                .title(book.getTitle())
                .skus(convertSkuToDTO(book.getSkus()))
                .author(book.getAuthor())
                .genre(book.getGenre())
                .createdDate(book.getCreatedDate())
                .modifiedDate(book.getModifiedDate())
                .price(book.getPrice())
                .build();
    }

    private List<SkuDTO> convertSkuToDTO(List<Sku> skus) {
        return skus.stream().map(sku -> SkuDTO.builder()
                .id(sku.getId())
                .status(sku.getStatus().name())
                .title(sku.getBook().getTitle())
                .stock(sku.getStock())
                .bookId(sku.getBook().getId())
                .build())
                .collect(Collectors.toList());
    }

    public Book convertSaveBookRequestToBook(SaveBookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .status(BaseStatus.Active)
                .author(request.getAuthor())
                .genre(Genre.valueOf(request.getGenre().toUpperCase()))
                .price(request.getPrice())
                .build();

        Sku sku = Sku.builder().status(BaseStatus.Active).book(book).stock(request.getStock()).build();
        book.setSkus(Collections.singletonList(sku));
        return book;
    }
}
