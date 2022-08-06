package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;

import java.util.List;

public interface CurrencyService {
    CurrencyDTO save(CurrencyDTO currencyDTO);

    CurrencyDTO update(CurrencyDTO currencyDTO);

    void delete(CurrencyDTO currencyDTO);

    List<CurrencyDTO> getAllCurrencies();

    void checkApplicationHasInitialCurrencies();

}
