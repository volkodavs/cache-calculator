package com.sergeyvolkodav.cachecalculator.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

public enum Operations {

    ADD(BigDecimal::add),
    SUBTRACT(BigDecimal::subtract),
    MULTIPLY(BigDecimal::multiply),
    DIVIDE((a, b) -> (a.divide(b, RoundingMode.HALF_UP)));

    private BinaryOperator<BigDecimal> operator;

    Operations(BinaryOperator<BigDecimal> operator) {
        this.operator = operator;
    }

    public BinaryOperator<BigDecimal> getOperator() {
        return operator;
    }
}
