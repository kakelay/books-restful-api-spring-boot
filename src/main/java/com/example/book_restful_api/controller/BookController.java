package com.example.book_restful_api.controller;

import com.example.book_restful_api.model.Book;
import com.example.book_restful_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String traceId, String message, String error, int statusCode) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("traceId", traceId);
        response.put("status", "fail");
        response.put("message", message);
        if (error != null) {
            response.put("error", error);
        }
        return ResponseEntity.status(statusCode).body(response);
    }
    private ResponseEntity<Map<String, Object>> buildErrorResponse(Map<String, Object> response, String message, String error) {
        response.put("status", "fail");
        response.put("message", message);
        if (error != null) {
            response.put("details", error);
        }
        return ResponseEntity.badRequest().body(response);
    }


    private ResponseEntity<Map<String, Object>> buildErrorResponse(String traceId, String message, int statusCode) {
        return buildErrorResponse(traceId, message, null, statusCode);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        String traceId = generateTraceId();

        try {
            List<Book> books = bookService.getAllBooks();
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("traceId", traceId);
            response.put("status", "success");
            response.put("message", "Books retrieved successfully.");
            response.put("books", books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to retrieve books.", e.getMessage(), 500);
        }
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        try {
            Optional<Book> book = bookService.getBookById(id);

            if (book.isPresent()) {
                response.put("status", "success");
                response.put("book", book.get());
                response.put("message", "Book retrieved successfully.");
                return ResponseEntity.ok(response);
            } else {
                return buildErrorResponse(response, "Book not found.",
                        String.format("The book with ID %d does not exist in the system.", id));
            }
        } catch (Exception e) {
            return buildErrorResponse(response, "Failed to retrieve book.", e.getMessage());
        }
    }


    // Create a new book
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        String traceId = generateTraceId();

        try {
            // Validation
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                return buildErrorResponse(traceId, "Book title is required.", 400);
            }
            if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
                return buildErrorResponse(traceId, "Book author is required.", 400);
            }
            if (book.getGenre() == null || book.getGenre().isEmpty()) {
                return buildErrorResponse(traceId, "Book genre is required.", 400);
            }
            if (book.getPrice() == null || book.getPrice() <= 0) {
                return buildErrorResponse(traceId, "Book price must be a positive number.", 400);
            }
            if (book.getPublished_year() == null || book.getPublished_year().length() != 4) {
                return buildErrorResponse(traceId, "Book published year must be a valid 4-digit year.", 400);
            }

            try {
                Integer.parseInt(book.getPublished_year());
            } catch (NumberFormatException e) {
                return buildErrorResponse(traceId, "Book published year must be a numeric value", 400);
            }

            // Create book
            Book createdBook = bookService.createBook(book);
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("traceId", traceId);
            response.put("status", "success");
            response.put("message", "Book created successfully");
            response.put("book", createdBook);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to create the book.", e.getMessage(), 500);
        }
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        String traceId = generateTraceId();

        try {
            // Validation
            if (bookDetails.getTitle() == null || bookDetails.getTitle().isEmpty()) {
                return buildErrorResponse(traceId, "Book title is required.", 400);
            }
            if (bookDetails.getAuthor() == null || bookDetails.getAuthor().isEmpty()) {
                return buildErrorResponse(traceId, "Book author is required.", 400);
            }
            if (bookDetails.getGenre() == null || bookDetails.getGenre().isEmpty()) {
                return buildErrorResponse(traceId, "Book genre is required.", 400);
            }
            if (bookDetails.getPrice() == null || bookDetails.getPrice() <= 0) {
                return buildErrorResponse(traceId, "Book price must be a positive number.", 400);
            }
            if (bookDetails.getPublished_year() == null || bookDetails.getPublished_year().length() != 4) {
                return buildErrorResponse(traceId, "Book published year must be a valid 4-digit year.", 400);
            }

            try {
                Integer.parseInt(bookDetails.getPublished_year());
            } catch (NumberFormatException e) {
                return buildErrorResponse(traceId, "Book published year must be a numeric value.", 400);
            }

            // Update book
            Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);
            if (updatedBook.isPresent()) {
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("traceId", traceId);
                response.put("status", "success");
                response.put("message", "Book updated successfully.");
                response.put("book", updatedBook.get());
                return ResponseEntity.ok(response);
            } else {
                return buildErrorResponse(traceId, "Book not found.", 404);
            }
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to update the book.", e.getMessage(), 500);
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        String traceId = generateTraceId();

        try {
            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) {
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("traceId", traceId);
                response.put("status", "success");
                response.put("message", "Book deleted successfully");
                response.put("bookId", id);
                response.put("timestamp", new Date().toString()); // Add timestamp for reference
                return ResponseEntity.ok(response);
            } else {
                return buildErrorResponse(traceId, "Book not found.", 404);
            }
        } catch (Exception e) {
            return buildErrorResponse(traceId, "Failed to delete the book.", e.getMessage(), 500);
        }
    }

}
