package com.example.book_restful_api.controller;


import com.example.book_restful_api.model.BookDetail;
import com.example.book_restful_api.service.BookDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book_detail")
public class BookDetailController {
    private final BookDetailService service;

    public BookDetailController(BookDetailService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookDetail> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetail> getBookById(@PathVariable Long id) {
        return service.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookDetail createBook(@RequestBody BookDetail bookDetail) {
        return service.saveBook(bookDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDetail> updateBook(@PathVariable Long id, @RequestBody BookDetail bookDetail) {
        return service.getBookById(id)
                .map(existingBook -> {
                    bookDetail.setId(existingBook.getId());
                    return ResponseEntity.ok(service.saveBook(bookDetail));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
