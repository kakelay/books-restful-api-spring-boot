package com.example.book_restful_api.controller;

import com.example.book_restful_api.model.BookDetail;
import com.example.book_restful_api.service.BookDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/book_detail")
public class BookDetailController {

    private final BookDetailService service;

    public BookDetailController(BookDetailService service) {
        this.service = service;
    }

    private String generateTraceId() {
        // Generate a random UUID as the trace ID
        return UUID.randomUUID().toString();
    }

    // Get all book details
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBookDetails() {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);

        try {
            List<BookDetail> bookDetails = service.getAllBooks();
            response.put("status", "success");
            response.put("bookDetails", bookDetails);
            response.put("message", "Book details retrieved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to retrieve book details.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Get book detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookDetailById(@PathVariable Long id) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookDetailId", id);

        try {
            Optional<BookDetail> bookDetail = service.getBookById(id);

            if (bookDetail.isPresent()) {
                response.put("status", "success");
                response.put("bookDetail", bookDetail.get());
                response.put("message", "Book detail retrieved successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "fail");
                response.put("message", "Book detail not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to retrieve book detail.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Create a new book detail
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBookDetail(@RequestBody BookDetail bookDetail) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);

        try {
            // Validate the incoming book detail object
            if (bookDetail.getTitle() == null || bookDetail.getTitle().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "Book title is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetail.getAuthor() == null || bookDetail.getAuthor().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "author: Book author is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetail.getGenre() == null || bookDetail.getGenre().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "genre: Book genre is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetail.getDescription() == null || bookDetail.getDescription().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "description: Book description is required.");
                return ResponseEntity.badRequest().body(response);
            }

            if (bookDetail.getPublished_year() == null || bookDetail.getPublished_year().length() != 4) {
                response.put("status", "fail");
                response.put("message", "published_year : Book published year must be a valid 4-digit year.");
                return ResponseEntity.badRequest().body(response);
            }
            // Create the book detail
            BookDetail createdBookDetail = service.saveBook(bookDetail);
            response.put("status", "success");
            response.put("bookDetail", createdBookDetail);
            response.put("message", "Book detail created successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to create the book detail.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Update a book detail
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBookDetail(@PathVariable Long id, @RequestBody BookDetail bookDetail) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookDetailId", id);

        try {
            Optional<BookDetail> existingBookDetail = service.getBookById(id);

            if (existingBookDetail.isPresent()) {
                bookDetail.setId(existingBookDetail.get().getId());
                BookDetail updatedBookDetail = service.saveBook(bookDetail);
                response.put("status", "success");
                response.put("bookDetail", updatedBookDetail);
                response.put("message", "Book detail updated successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "fail");
                response.put("message", "Book detail not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to update the book detail.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Delete a book detail
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBookDetail(@PathVariable Long id) {
        String traceId = generateTraceId();
        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("bookDetailId", id);

        try {
            boolean isDeleted = service.deleteBook(id);

            if (isDeleted) {
                response.put("status", "success");
                response.put("message", "Book detail deleted successfully.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "fail");
                response.put("message", "Book detail not found.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("status", "fail");
            response.put("message", "Failed to delete the book detail.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


}
