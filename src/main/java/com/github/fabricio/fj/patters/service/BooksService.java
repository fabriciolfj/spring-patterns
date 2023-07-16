package com.github.fabricio.fj.patters.service;

import com.github.fabricio.fj.patters.client.Book;
import com.github.fabricio.fj.patters.client.BooksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksClient booksClient;

    public List<Book> getAllBooks() {
        return this.booksClient.getBooks();
    }
}
