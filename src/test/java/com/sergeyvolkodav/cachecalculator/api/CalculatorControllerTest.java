package com.sergeyvolkodav.cachecalculator.api;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.sergeyvolkodav.cachecalculator.Application;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpServletResponse;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
@WebAppConfiguration
public class CalculatorControllerTest {


    @BeforeClass
    public static void setupURL() {
        RestAssuredMockMvc.basePath = "/rest/v1/calculator";

    }

    @Test
    @Ignore
    public void add() throws Exception {
        given().
                contentType("application/json").
                when().
                get("/add/1/2/97").
                then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body("result", equalTo("100"));
    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

    @Test
    public void divide() throws Exception {

    }

    @Test
    public void handleUncheckedExceptions() throws Exception {

    }
}