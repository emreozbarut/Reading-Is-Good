package com.readingisgood.controller;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.request.book.SaveBookRequest;
import com.readingisgood.response.book.SaveBookResponse;
import com.readingisgood.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @ResponseBody
    @PostMapping("/save")
    public SaveBookResponse save(@RequestBody SaveBookRequest saveBookRequest) {
        return bookService.saveBook(saveBookRequest);
    }
}
