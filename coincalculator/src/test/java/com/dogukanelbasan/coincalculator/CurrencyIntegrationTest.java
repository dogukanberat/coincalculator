package com.dogukanelbasan.coincalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CurrencyIntegrationTest {

    @Test
    void test_isApiAlive() throws Exception {
        Response res = RestAssured.get("https://api.blockchain.com/v3/exchange/tickers/" + "BTC" + CurrencyConstants.DELIMITER + "EUR");
        assertEquals(200,res.statusCode());
        assertEquals("BTC-EUR", res.getBody().jsonPath().getString("symbol"));
    }

}
