package com.example.book_restful_api.controller;

import com.example.book_restful_api.model.BookDetail;
import com.example.book_restful_api.service.BookDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/book_detail")
public class BookDetailController {

    private final BookDetailService service;

    public BookDetailController(BookDetailService service) {
        this.service = service;
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    private ResponseEntity<Map<String, Object>> buildResponse(String status, String message, Object data, String traceId, int statusCode) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", status);
        response.put("message", message);
        response.put("responseDate", new Date());
        response.put("traceId", traceId);
        response.put("data", data);

        return ResponseEntity.status(statusCode).body(response);
    }

    private ResponseEntity<Map<String, Object>> handleException(String traceId, String action, Exception e) {
        return buildResponse(traceId, "fail", "Failed to " + action, e.getMessage(), 500);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBookDetails() {
        String traceId = generateTraceId();
        try {
            List<BookDetail> bookDetails = service.getAllBooksDetails();
            return buildResponse("success", "Books retrieved successfully", bookDetails, traceId, 200);
        } catch (Exception e) {
            return handleException(traceId, "retrieve book details", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookDetailById(@PathVariable Long id) {
        String traceId = generateTraceId();
        try {
            Optional<BookDetail> bookDetail = service.getBookById(id);
            if (bookDetail.isPresent()) {
                return buildResponse( "success", "Book detail retrieved successfully", bookDetail.get(),traceId, 200);
            } else {
                return buildResponse(traceId, "fail", "Book detail not found", null, 404);
            }
        } catch (Exception e) {
            return handleException(traceId, "retrieve book detail", e);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBookDetail(@RequestBody BookDetail bookDetail) {
        String traceId = generateTraceId();
        try {
            String validationError = validateBookDetail(bookDetail);
            if (validationError != null) {
                return buildResponse(traceId, "fail", validationError, null, 400);
            }
            BookDetail createdBookDetail = service.saveBook(bookDetail);
            return buildResponse(  "success", "Book detail created successfully", createdBookDetail, traceId,201);
        } catch (Exception e) {
            return handleException(traceId, "create the book detail", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBookDetail(@PathVariable Long id, @RequestBody BookDetail bookDetail) {
        String traceId = generateTraceId();
        try {
            Optional<BookDetail> existingBookDetail = service.getBookById(id);
            if (existingBookDetail.isPresent()) {
                bookDetail.setId(existingBookDetail.get().getId());
                BookDetail updatedBookDetail = service.saveBook(bookDetail);
                return buildResponse(  "success", "Book detail updated successfully", updatedBookDetail, traceId,200);
            } else {
                return buildResponse(traceId, "fail", "Book detail not found", null, 404);
            }
        } catch (Exception e) {
            return handleException(traceId, "update the book detail", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBookDetail(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String traceId = generateTraceId();
        try {
            if (!request.containsKey("confirmStatus")) {
                return buildResponse(traceId, "fail", "confirmStatus is required", null, 400);
            }

            String confirmStatus = request.get("confirmStatus");
            if (!"yes".equalsIgnoreCase(confirmStatus)) {
                return buildResponse(traceId, "fail", "Deletion need to confirmed first", null, 400);
            }

            boolean isDeleted = service.deleteBook(id);
            if (isDeleted) {
                return buildResponse(traceId, "success", "Book detail deleted successfully", null, 200);
            } else {
                return buildResponse(traceId, "fail", "Book detail not found", null, 404);
            }
        } catch (Exception e) {
            return handleException(traceId, "delete the book detail", e);
        }
    }

    private String validateBookDetail(BookDetail bookDetail) {
        if (bookDetail.getTitle() == null || bookDetail.getTitle().isEmpty()) {
            return "Book title is required";
        }
        if (bookDetail.getAuthor() == null || bookDetail.getAuthor().isEmpty()) {
            return "Book author is required";
        }
        if (bookDetail.getGenre() == null || bookDetail.getGenre().isEmpty()) {
            return "Book genre is required";
        }
        if (bookDetail.getDescription() == null || bookDetail.getDescription().isEmpty()) {
            return "Book description is required";
        }
        if (bookDetail.getPublished_year() == null || bookDetail.getPublished_year().length() != 4) {
            return "Book published year must be a valid 4-digit year";
        }
        return null;
    }
}
