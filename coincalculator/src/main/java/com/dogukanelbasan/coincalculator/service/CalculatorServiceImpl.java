package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.client.BlockChainClient;
import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyResponseDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.exception.CoinException;
import com.dogukanelbasan.coincalculator.utils.Constants;
import com.dogukanelbasan.coincalculator.validation.CurrencyFiatValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private BlockChainClient blockChainClient;

    @Autowired
    private CurrencyFiatValidator currencyFiatValidator;

    @Override
    public CurrencyToCryptoCurrencyDTO prices(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO) {
        currencyFiatValidator.isValid(fiatCurrencyToCryptoCurrencyDTO);

        String fiatToCurrency = generateTickersUrl(fiatCurrencyToCryptoCurrencyDTO.getCrypto().getCurrency() , fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency().getCurrency());
        CurrencyResponseDTO currencyResponse;
        try{
            currencyResponse =  blockChainClient.getCurrency(fiatToCurrency);
        }catch (Exception e){
            throw new CoinException(e,Constants.REST_REQUEST_ERROR);
        }
        double currencyAmount = Double.parseDouble(currencyResponse.getLast_trade_price());
        boolean calculateWithFiatCurrency = Constants.ORDER_TYPE.FIAT.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type());
        boolean calculateWithCrypto = Constants.ORDER_TYPE.CRYPTO.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type());

        CurrencyDTO currencyResponseDTO;

        if (calculateWithFiatCurrency ) {
            currencyResponseDTO = fiatCurrencyToCryptoCurrencyDTO.getCrypto();
            currencyAmount = fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency().getAmount().doubleValue() / currencyAmount;
            currencyResponseDTO.setAmount(currencyAmount);
            fiatCurrencyToCryptoCurrencyDTO.setCrypto(currencyResponseDTO);
        } else if (calculateWithCrypto) {
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

