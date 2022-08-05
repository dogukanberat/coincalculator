package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.client.BlockChainClient;
import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyResponseDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.enums.OrderType;
import com.dogukanelbasan.coincalculator.exception.CoinException;
import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;
import com.dogukanelbasan.coincalculator.validation.CurrencyFiatValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new CoinException(e, CurrencyConstants.REST_REQUEST_ERROR);
        }
        double currencyAmount = Double.parseDouble(currencyResponse.getLast_trade_price());
        boolean calculateWithFiatCurrency = OrderType.FIAT.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrderType());
        boolean calculateWithCrypto = OrderType.CRYPTO.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrderType());

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
        return  currency + CurrencyConstants.DELIMITER + fiatCurrency;
    }
}

