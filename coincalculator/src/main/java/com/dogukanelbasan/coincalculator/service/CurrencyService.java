package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.model.CurrencyModel;

import java.util.List;

public interface CurrencyService {
    CurrencyModel save(CurrencyModel currencyDTO);

    CurrencyModel update(CurrencyModel currencyModel);

    void delete(CurrencyModel currencyModel);

    List<CurrencyModel> getAllCurrencies();

    void checkApplicationHasInitialCurrencies();

}
