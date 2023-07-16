package com.github.fabricio.fj.patters.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface BooksClient {

    @GetExchange("/books")
    List<Book> getBooks();

    @GetExchange("/books/{id}")
    Book getBook(@PathVariable final String id);

    @PostExchange("/books")
    Book saveBook(@RequestBody final Book book);

    @DeleteExchange("/books/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable final String id);

}
