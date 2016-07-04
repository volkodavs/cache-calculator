package com.sergeyvolkodav.cachecalculator.api;


import com.sergeyvolkodav.cachecalculator.api.data.ResponseData;
import com.sergeyvolkodav.cachecalculator.api.enums.AppStatusCodes;
import com.sergeyvolkodav.cachecalculator.services.ICalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sergeyvolkodav.cachecalculator.api.CalculatorController.PATH;
import static com.sergeyvolkodav.cachecalculator.api.validations.CalculatorValidator.validateMaxValues;
import static com.sergeyvolkodav.cachecalculator.api.validations.CalculatorValidator.validateNotNullValue;

@RestController
@RequestMapping(value = PATH)
public class CalculatorController {

    static final String PATH = "/rest/v1/calculator";

    private static final String ADD_PATH = "/add/**";
    private static final String SUBTRACT_PATH = "/subtract/**";
    private static final String MULTIPLY_PATH = "/multiply/**";
    private static final String DIVIDE_PATH = "/divide/**";


    private static Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    ICalculator calculator;

    @RequestMapping(value = ADD_PATH, method = RequestMethod.GET)
    public ResponseData add(HttpServletRequest request) {
        String values = pathSubstring(request, ADD_PATH);
        validateMaxValues(values);
        validateNotNullValue(values);
        log.info("Calculate sum for values: {}", values);
        return new ResponseData(AppStatusCodes.StatusOk,
                calculator.sum(convertToDecimal(values)).toString());
    }

    @RequestMapping(value = SUBTRACT_PATH, method = RequestMethod.GET)
    public ResponseData subtract(HttpServletRequest request) {
        String values = pathSubstring(request, SUBTRACT_PATH);
        validateMaxValues(values);
        validateNotNullValue(values);
        log.info("Calculate subtract for values: {}", values);
        return new ResponseData(AppStatusCodes.StatusOk,
                calculator.subtract(convertToDecimal(values)).toString());
    }

    @RequestMapping(value = MULTIPLY_PATH, method = RequestMethod.GET)
    public ResponseData multiply(HttpServletRequest request) {
        String values = pathSubstring(request, MULTIPLY_PATH);
        validateMaxValues(values);
        validateNotNullValue(values);
        log.info("Calculate multiply for values: {}", values);
        return new ResponseData(AppStatusCodes.StatusOk,
                calculator.multiply(convertToDecimal(values)).toString());
    }

    @RequestMapping(value = DIVIDE_PATH, method = RequestMethod.GET)
    public ResponseData divide(HttpServletRequest request) {
        String values = pathSubstring(request, DIVIDE_PATH);
        validateMaxValues(values);
        validateNotNullValue(values);
        log.info("Calculate divide for values: {}", values);
        return new ResponseData(AppStatusCodes.StatusOk,
                calculator.divide(convertToDecimal(values)).toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData handleUncheckedExceptions(Exception ex) {
        log.warn("Unchecked exception:", ex);
        return new ResponseData(AppStatusCodes.StatusError, ex);
    }


    private String pathSubstring(HttpServletRequest request, String action) {
        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return restOfTheUrl.substring(PATH.length() + action.length() - 2);
    }

    private List<BigDecimal> convertToDecimal(String values) {
        String delimiter = "\\/";
        String[] normalizeValues = values.replace(",", ".").split(delimiter);
        return Arrays.asList(normalizeValues).stream()
                .map(BigDecimal::new).map(e -> e.setScale(2, RoundingMode.HALF_UP))
                .collect(Collectors.toList());
    }

}


