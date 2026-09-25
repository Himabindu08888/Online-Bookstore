package com.bookstore.app.controller;

import com.bookstore.app.model.Book;
import com.bookstore.app.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@GetMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String keyword,
                             @RequestParam(required = false) String genre,
                             Model model) {
        java.util.List<Book> books;
        if (genre != null && !genre.isBlank()) {
            books = bookService.findByGenre(genre);
        } else {
            books = bookService.search(keyword);
        }
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre", genre);
        return "books";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book-detail";
    }
}
