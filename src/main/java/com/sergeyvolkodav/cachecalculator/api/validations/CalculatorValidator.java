package com.sergeyvolkodav.cachecalculator.api.validations;

public class CalculatorValidator {

    private static final Integer MAX_VALUES = 3;

    public static void validateMaxValues(String values) {
        int counter = 1;
        for (int i = 0; i < values.length(); i++) {
            if (values.charAt(i) == '/') {
                counter++;
            }
        }
        if (counter > MAX_VALUES) {
            throw new RuntimeException("Max calculation values should be less than: " + MAX_VALUES);
        }
    }

    public static void validateNotNullValue(String values) {
        if ( values == null || values.isEmpty()) {
            throw new RuntimeException("Calculation values can't be null");
        }
    }
}
