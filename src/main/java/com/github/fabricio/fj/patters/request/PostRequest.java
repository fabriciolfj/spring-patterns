package com.github.fabricio.fj.patters.request;

public record PostRequest(Long userId, Long id, String title, String body) {
}
