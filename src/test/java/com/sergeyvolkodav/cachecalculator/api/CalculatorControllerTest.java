package com.sergeyvolkodav.cachecalculator.api;

import com.jayway.restassured.RestAssured;
import com.sergeyvolkodav.cachecalculator.Application;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CalculatorControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/rest/v1/calculator";
    }

    @Test
    public void add() throws Exception {
        when().
                get("/add/1/2/97").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("100.00"));
    }

    @Test
    public void addOneValue() throws Exception {
        when().
                get("/add/1").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("1.00"));
    }

    @Test
    public void subtract() throws Exception {
        when().
                get("/subtract/200/150.20").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("49.80"));
    }

    @Test
    public void subtractOneValue() throws Exception {
        when().
                get("/subtract/1").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("1.00"));
    }

    @Test
    public void multiply() throws Exception {
        when().
                get("/multiply/2.02/-0,1/100").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("-20.200000"));
    }

    @Test
    public void multiplyOneValue() throws Exception {
        when().
                get("/multiply/2.1").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("2.10"));
    }

    @Test
    public void divide() throws Exception {
        when().
                get("/divide/8/4/2").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("1.00"));
    }

    @Test
    public void divideOneValue() throws Exception {
        when().
                get("/divide/8").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body("result", equalTo("8.00"));
    }

    @Test
    public void handleUncheckedExceptions() throws Exception {
        when().
                get("/divide/8/0").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                contentType("application/json").
                body("status", equalTo("error"));
    }

    @Test
    public void handleUncheckedExceptionsToManyValues() throws Exception {
        when().
                get("/divide/8/2/2/2").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                contentType("application/json").
                body("status", equalTo("error"));
    }
}