package com.github.fabricio.fj.patters.strategy.impl;

import com.github.fabricio.fj.patters.strategy.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service("paypal")
public class PaypalPayment implements PaymentMethod {

    @Override
    public void execute(final BigDecimal value) {
        log.info("Payment executed paypal, value = {}", value);
    }
}
