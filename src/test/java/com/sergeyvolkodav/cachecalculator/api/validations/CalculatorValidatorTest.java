package com.sergeyvolkodav.cachecalculator.api.validations;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CalculatorValidatorTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void validateMaxValues() throws Exception {
        thrown.expect(RuntimeException.class);
        CalculatorValidator.validateMaxValues("10/10/10/2");
    }

    @Test
    public void validateNotNullValue() throws Exception {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Calculation values can't be null");
        CalculatorValidator.validateNotNullValue("");
    }

    @Test
    public void validateNotNullValueIsOk() throws Exception {
        CalculatorValidator.validateNotNullValue("110/10");
    }
}