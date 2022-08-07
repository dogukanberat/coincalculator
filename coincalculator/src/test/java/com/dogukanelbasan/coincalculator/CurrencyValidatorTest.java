package com.dogukanelbasan.coincalculator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.entity.Currency;
import com.dogukanelbasan.coincalculator.enums.OrderType;
import com.dogukanelbasan.coincalculator.exception.CoinException;
import com.dogukanelbasan.coincalculator.repository.CurrencyRepository;
import com.dogukanelbasan.coincalculator.validation.CurrencyFiatValidatorImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CurrencyValidatorTest {

    @InjectMocks
    CurrencyFiatValidatorImpl currencyFiatValidatorImpl = new CurrencyFiatValidatorImpl();

    @Mock
    CurrencyRepository currencyRepository;

    @Test
    public void test_fiatMaxAmount() {
        // Given
        Currency fiatCurrency = new Currency();
        fiatCurrency.setCurrency("USD");
        fiatCurrency.setSpendable(true);
        fiatCurrency.setReceivable(false);
        fiatCurrency.setMaxSpendAmount(5000);
        fiatCurrency.setMinSpendAmount(25);
        fiatCurrency.setIsFiatCurrency(true);

        Currency cryptoCurrency = new Currency();
        cryptoCurrency.setCurrency("BTC");
        cryptoCurrency.setSpendable(false);
        cryptoCurrency.setReceivable(true);
        cryptoCurrency.setMaxSpendAmount(5000);
        cryptoCurrency.setMinSpendAmount(25);
        cryptoCurrency.setIsFiatCurrency(false);

        CurrencyToCryptoCurrencyDTO currencyToCryptoCurrencyDTO = new CurrencyToCryptoCurrencyDTO();
        CurrencyDTO cryptoDTO = new CurrencyDTO();
        cryptoDTO.setAmount(500);
        cryptoDTO.setCurrency("BTC");

        CurrencyDTO fiatDTO = new CurrencyDTO();
        fiatDTO.setAmount(5001);
        fiatDTO.setCurrency("USD");

        currencyToCryptoCurrencyDTO.setOrderType(OrderType.FIAT.value());
        currencyToCryptoCurrencyDTO.setFiatCurrency(fiatDTO);
        currencyToCryptoCurrencyDTO.setCrypto(cryptoDTO);

        // When
        when(currencyRepository.findByCurrency("USD")).thenReturn(fiatCurrency);
        when(currencyRepository.findByCurrency("BTC")).thenReturn(cryptoCurrency);

        // Then
        boolean hasError = false;
        String errorMessage = "";
        try {
            currencyFiatValidatorImpl.isValid(currencyToCryptoCurrencyDTO);
        } catch (CoinException coinException) {
            errorMessage = coinException.getMessage();
            System.out.println(errorMessage);
            hasError = true;
        }

        assertTrue(hasError, "Should have error because max amount is exceeded");
    }

    @Test
    public void test_fiatMinAmount() {
        // Given
        Currency fiatCurrency = new Currency();
        fiatCurrency.setCurrency("USD");
        fiatCurrency.setSpendable(true);
        fiatCurrency.setReceivable(false);
        fiatCurrency.setMaxSpendAmount(5000);
        fiatCurrency.setMinSpendAmount(25);
        fiatCurrency.setIsFiatCurrency(true);

        Currency cryptoCurrency = new Currency();
        cryptoCurrency.setCurrency("BTC");
        cryptoCurrency.setSpendable(false);
        cryptoCurrency.setReceivable(true);
        cryptoCurrency.setMaxSpendAmount(5000);
        cryptoCurrency.setMinSpendAmount(25);
        cryptoCurrency.setIsFiatCurrency(false);

        CurrencyToCryptoCurrencyDTO currencyToCryptoCurrencyDTO = new CurrencyToCryptoCurrencyDTO();
        CurrencyDTO cryptoDTO = new CurrencyDTO();
        cryptoDTO.setAmount(500);
        cryptoDTO.setCurrency("BTC");

        CurrencyDTO fiatDTO = new CurrencyDTO();
        fiatDTO.setAmount(23);
        fiatDTO.setCurrency("USD");

        currencyToCryptoCurrencyDTO.setOrderType(OrderType.FIAT.value());
        currencyToCryptoCurrencyDTO.setFiatCurrency(fiatDTO);
        currencyToCryptoCurrencyDTO.setCrypto(cryptoDTO);

        // When
        when(currencyRepository.findByCurrency("USD")).thenReturn(fiatCurrency);
        when(currencyRepository.findByCurrency("BTC")).thenReturn(cryptoCurrency);

        // Then
        boolean hasError = false;
        String errorMessage = "";
        try {
            currencyFiatValidatorImpl.isValid(currencyToCryptoCurrencyDTO);
        }catch (CoinException coinException){
            errorMessage = coinException.getMessage();
            System.out.println(errorMessage);
            hasError = true;
        }

        assertTrue(hasError,"Should have error because amount is less than minimum amount");
    }

}
