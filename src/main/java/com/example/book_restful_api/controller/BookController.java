package com.example.book_restful_api.controller;

import com.example.book_restful_api.model.Book;
import com.example.book_restful_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    private ResponseEntity<Map<String, Object>> buildResponse(String status, String message, Object data, String traceId, int statusCode) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("traceId", traceId);
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);
        response.put("responseDate", new Date()); // Add responseDate field
        return ResponseEntity.status(statusCode).body(response);
    }


    private ResponseEntity<Map<String, Object>> buildErrorResponse(String traceId, String message, String error, int statusCode) {
        return buildResponse("fail", message, Collections.singletonMap("error", error), traceId, statusCode);
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) throw new IllegalArgumentException("Book title is required.");
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) throw new IllegalArgumentException("Book author is required.");
        if (book.getGenre() == null || book.getGenre().isEmpty()) throw new IllegalArgumentException("Book genre is required.");
        if (book.getPrice() == null || book.getPrice() <= 0) throw new IllegalArgumentException("Book price must be a positive number.");
        if (book.getPublished_year() == null || book.getPublished_year().length() != 4 || !book.getPublished_year().matches("\\d{4}"))
            throw new IllegalArgumentException("Book published year must be a valid 4-digit year.");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        String traceId = generateTraceId();
        try {
            List<Book> books = bookService.getAllBooks();
            return buildResponse("success", "Books retrieved successfully.", books, traceId, 200);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to retrieve books", e.getMessage(), 500);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id) {
        String traceId = generateTraceId();
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isPresent()) {
                return buildResponse("success", "Book retrieved successfully.", book.get(), traceId, 200);
            }
            return buildErrorResponse(traceId, "Book not found.", "The book with ID " + id + " does not exist.", 404);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to retrieve book", e.getMessage(), 500);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        String traceId = generateTraceId();
        try {
            validateBook(book);
            Book createdBook = bookService.createBook(book);
            return buildResponse("success", "Book created successfully.", createdBook, traceId, 201);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(traceId, e.getMessage(), null, 400);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to create the book.", e.getMessage(), 500);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        String traceId = generateTraceId();
        try {
            validateBook(bookDetails);
            Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);
            if (updatedBook.isPresent()) {
                return buildResponse("success", "Book updated successfully", updatedBook.get(), traceId, 200);
            }
            return buildErrorResponse(traceId, "Book not found", null, 404);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(traceId, e.getMessage(), null, 400);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to update the book.", e.getMessage(), 500);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        String traceId = generateTraceId();
        try {
            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) {
                return buildResponse("success", "Book deleted successfully", Map.of("bookId", id), traceId, 200);
            }
            return buildErrorResponse(traceId, "Book not found.", null, 404);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to delete the book.", e.getMessage(), 500);
        }
    }
}
