package com.github.fabricio.fj.patters.strategy;

import java.math.BigDecimal;

public interface PaymentMethod {

    void execute(final BigDecimal value);
}
