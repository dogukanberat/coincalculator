package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;

public interface CalculatorService {
    CurrencyToCryptoCurrencyDTO prices(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO);
}
