package com.sergeyvolkodav.cachecalculator.services;

import com.sergeyvolkodav.cachecalculator.Application;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CalculatorServiceTest {


    @Autowired
    CalculatorService calculatorService;

    private List<BigDecimal> listValues;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        listValues = new ArrayList<>();
        listValues.add(new BigDecimal("100"));
        listValues.add(new BigDecimal("50"));
        listValues.add(new BigDecimal("2"));
    }

    @Test
    public void calculationAdd() throws Exception {
        BigDecimal calculation = calculatorService.calculation(listValues, Operations.ADD);
        assertThat(calculation, is(new BigDecimal("152")));
    }

    @Test
    public void calculationDivide() throws Exception {
        BigDecimal calculation = calculatorService.calculation(listValues, Operations.DIVIDE);
        assertThat(calculation, is(new BigDecimal("1")));
    }

    @Test
    public void calculationDivideException() throws Exception {
        List<BigDecimal> listException = new ArrayList<>();
        listValues.add(BigDecimal.TEN);
        listValues.add(BigDecimal.ZERO);
        thrown.expect(RuntimeException.class);
        calculatorService.calculation(listException, Operations.DIVIDE);
    }

    @Test
    public void calculationMultiply() throws Exception {
        BigDecimal calculation = calculatorService.calculation(listValues, Operations.MULTIPLY);
        assertThat(calculation, is(new BigDecimal("10000")));
    }

    @Test
    public void calculationSubtract() throws Exception {
        BigDecimal calculation = calculatorService.calculation(listValues, Operations.SUBTRACT);
        assertThat(calculation, is(new BigDecimal("48")));
    }

}