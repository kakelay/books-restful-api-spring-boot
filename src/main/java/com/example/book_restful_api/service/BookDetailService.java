package com.example.book_restful_api.service;

import com.example.book_restful_api.model.BookDetail;
import com.example.book_restful_api.respository.BookDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailService {
    private final BookDetailRepository repository;

    public BookDetailService(BookDetailRepository repository) {
        this.repository = repository;
    }

    public List<BookDetail> getAllBooksDetails() {
        return repository.findAll();
    }

    public Optional<BookDetail> getBookById(Long id) {
        return repository.findById(id);
    }

    public BookDetail saveBook(BookDetail bookDetail) {
        return repository.save(bookDetail);
    }

    public boolean deleteBook(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
