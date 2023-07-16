package com.github.fabricio.fj.patters.config;

import com.github.fabricio.fj.patters.client.BooksClient;
import com.github.fabricio.fj.patters.exceptions.BookServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
public class BookClientConfig {

    private final String url;

    public BookClientConfig(@Value("${service.url}") final String url) {
        this.url = url;
    }

    @Bean
    public BooksClient bookService() {
        final HttpServiceProxyFactory proxy = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(
                        WebClient.builder()
                                .baseUrl(this.url)
                                .defaultStatusHandler(HttpStatusCode::isError, resp ->
                                        Mono.just(new BookServiceException(resp.toString())))
                                .build()
                )).build();

        return proxy.createClient(BooksClient.class);
    }
}
