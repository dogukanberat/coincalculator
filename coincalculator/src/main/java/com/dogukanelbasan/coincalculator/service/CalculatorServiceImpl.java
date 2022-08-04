package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.client.BlockChainClient;
import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyResponseDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.exception.CoinException;
import com.dogukanelbasan.coincalculator.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private BlockChainClient blockChainClient;

    @Override
    public Object prices(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO) {
        String fiatToCurrency = generateTickersUrl(fiatCurrencyToCryptoCurrencyDTO.getCrypto().getCurrency() , fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency().getCurrency());
        CurrencyResponseDTO currencyResponse = null;
        try{
            currencyResponse =  blockChainClient.getCurrency(fiatToCurrency);
        }catch (Exception e){
            throw new CoinException(e,Constants.REST_REQUEST_ERROR);
        }
        double currencyAmount = Double.parseDouble(currencyResponse.getPrice_24h());

        CurrencyDTO currencyResponseDTO = null;
        if (Constants.ORDER_TYPE.BUY.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type()) ) {
            currencyResponseDTO = fiatCurrencyToCryptoCurrencyDTO.getCrypto();
            currencyAmount = fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency().getAmount().doubleValue() / currencyAmount;
            currencyResponseDTO.setAmount(currencyAmount);
            fiatCurrencyToCryptoCurrencyDTO.setCrypto(currencyResponseDTO);

        } else if (Constants.ORDER_TYPE.SELL.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type())) {
            currencyResponseDTO = fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency();
            currencyAmount = fiatCurrencyToCryptoCurrencyDTO.getCrypto().getAmount().doubleValue() * currencyAmount;
            currencyResponseDTO.setAmount(currencyAmount);
            fiatCurrencyToCryptoCurrencyDTO.setFiatCurrency(currencyResponseDTO);
        }else{
            throw new RuntimeException();
        }
        return fiatCurrencyToCryptoCurrencyDTO;
    }

    private String generateTickersUrl(String currency,String fiatCurrency) {
        return  currency + Constants.DELIMITER + fiatCurrency;
    }
}

