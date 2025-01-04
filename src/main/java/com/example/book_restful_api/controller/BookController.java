package com.example.book_restful_api.controller;

import com.example.book_restful_api.model.Book;
import com.example.book_restful_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private String generateTraceId() {
        // Generate a random UUID as the trace ID
        return UUID.randomUUID().toString();
    }

    // Get all books
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("books", books);
        response.put("message", "Books retrieved successfully.");
        return ResponseEntity.ok(response);
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        if (book.isPresent()) {
            response.put("book", book.get());
            response.put("message", "Book retrieved successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Book not found.");
            return ResponseEntity.status(404).body(response);
        }
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("book", createdBook);
        response.put("message", "Book created successfully.");
        return ResponseEntity.ok(response);
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        if (updatedBook.isPresent()) {
            response.put("book", updatedBook.get());
            response.put("message", "Book updated successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Book not found.");
            return ResponseEntity.status(404).body(response);
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteBook(id);
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        if (isDeleted) {
            response.put("message", "Book deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Book not found.");
            return ResponseEntity.status(404).body(response);
        }
    }
}
