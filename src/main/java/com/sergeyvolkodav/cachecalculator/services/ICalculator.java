package com.sergeyvolkodav.cachecalculator.services;

import java.math.BigDecimal;
import java.util.List;

public interface ICalculator {


    BigDecimal sum(List<BigDecimal> values);

    BigDecimal subtract(List<BigDecimal> values);

    BigDecimal multiply(List<BigDecimal> values);

    BigDecimal divide(List<BigDecimal> values);

}
