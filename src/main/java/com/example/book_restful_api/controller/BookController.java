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
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);

        try {
            List<Book> books = bookService.getAllBooks();
            response.put("status", "success");
            response.put("books", books);
            response.put("message", "Books retrieved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to retrieve books.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
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
                response.put("status", "fail");
                response.put("message", "Book not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to retrieve book.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    // Create a new book
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);

        try {
            // Validate the incoming book object
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book title is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book author is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (book.getGenre() == null || book.getGenre().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book genre is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (book.getPrice() == null || book.getPrice() <= 0) {
                response.put("status", "fail");
                response.put("message", "Book price must be a positive number.");
                return ResponseEntity.badRequest().body(response);
            }

            if (book.getPublished_year() == null || book.getPublished_year().length() != 4) {
                response.put("status", "fail");
                response.put("message", "Book published year must be a valid 4-digit year.");
                return ResponseEntity.badRequest().body(response);
            }

            try {
                Integer.parseInt(book.getPublished_year());
            } catch (NumberFormatException e) {
                response.put("status", "fail");
                response.put("message", "Book published year must be a numeric value.");
                return ResponseEntity.badRequest().body(response);
            }

            // Create the book
            Book createdBook = bookService.createBook(book);
            response.put("status", "success");
            response.put("book", createdBook);
            response.put("message", "Book created successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle unexpected errors
            response.put("status", "fail");
            response.put("message", "Failed to create the book.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }



    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        try {
            // Validate incoming book details
            if (bookDetails.getTitle() == null || bookDetails.getTitle().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book title is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetails.getAuthor() == null || bookDetails.getAuthor().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book author is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetails.getGenre() == null || bookDetails.getGenre().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book genre is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetails.getPrice() == null || bookDetails.getPrice() <= 0) {
                response.put("status", "fail");
                response.put("message", "Book price must be a positive number.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetails.getPublished_year() == null || bookDetails.getPublished_year().length() != 4) {
                response.put("status", "fail");
                response.put("message", "Book published year must be a valid 4-digit year.");
                return ResponseEntity.badRequest().body(response);
            }

            try {
                Integer.parseInt(bookDetails.getPublished_year());
            } catch (NumberFormatException e) {
                response.put("status", "fail");
                response.put("message", "Book published year must be a numeric value.");
                return ResponseEntity.badRequest().body(response);
            }

            // Update the book
            Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);

            if (updatedBook.isPresent()) {
                response.put("status", "success");
                response.put("book", updatedBook.get());
                response.put("message", "Book updated successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "fail");
                response.put("message", "Book not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            // Handle unexpected errors
            response.put("status", "fail");
            response.put("message", "Failed to update the book.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookId", id);

        try {
            boolean isDeleted = bookService.deleteBook(id);

            if (isDeleted) {
                response.put("status", "success");
                response.put("message", "Book deleted successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "fail");
                response.put("message", "Book not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            // Handle unexpected errors
            response.put("status", "fail");
            response.put("message", "Failed to delete the book.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
