package com.github.fabricio.fj.patters.controller;

import com.github.fabricio.fj.patters.strategy.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final Map<String, PaymentMethod> payments;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{value}/{method}")
    public void paymentProcessing(@PathVariable("value") final String value, @PathVariable("method") final String method) {
        var process = payments.get(method);
        process.execute(new BigDecimal(value));
    }
}
