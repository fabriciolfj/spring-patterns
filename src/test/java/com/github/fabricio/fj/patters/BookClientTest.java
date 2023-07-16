package com.github.fabricio.fj.patters;


import com.github.fabricio.fj.patters.client.Book;
import com.github.fabricio.fj.patters.client.BooksClient;
import com.github.fabricio.fj.patters.service.BooksService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class BookClientTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebClient webClient;

    @Autowired
    private BooksClient booksClient;

    @Test
    void testGetBook() {
        given(webClient.method(HttpMethod.GET)
                .uri(anyString(), anyMap())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>(){}))
                .willReturn(Mono.just(List.of(
                        new Book(1,"Book_1"),
                        new Book(2, "Book_2")
                )));

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        var service = httpServiceProxyFactory.createClient(BooksClient.class);

        var books = service.getBooks();
        Book book = books.get(0);
        assertEquals("Book_1", book.getDescribe());
    }
}
