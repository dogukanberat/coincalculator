package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.client.BlockChainClient;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private BlockChainClient blockChainClient;

    @Override
    public Object prices(CurrencyToCryptoCurrencyDTO calculateDTO) {
        return blockChainClient.getCurrency("BTC-USD");
    }
}

