package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;

public interface CalculatorService {
    Object prices(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO);
}
