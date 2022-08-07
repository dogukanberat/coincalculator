package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.entity.Currency;
import com.dogukanelbasan.coincalculator.model.CurrencyModel;
import com.dogukanelbasan.coincalculator.repository.CurrencyRepository;
import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public CurrencyModel save(CurrencyModel currencyModel) {
        Currency currency = currencyRepository.findByCurrency(currencyModel.getCurrency());
        if (currency != null) {
            throw new RuntimeException(CurrencyConstants.CURRENCY_ALREADY_EXIST);
        }
        Currency currency1 = currencyModel.toEntity();
        currencyRepository.save(currency1);
        return currencyModel;
    }

    @Override
    public CurrencyModel update(CurrencyModel currencyModel) {
        Currency currency = currencyRepository.findByCurrency(currencyModel.getCurrency());
        if (currency != null) {
            currency.setIsFiatCurrency(currencyModel.getIsFiatCurrency());
            currency.setSpendable(currencyModel.getSpendable());
            currency.setReceivable(currencyModel.getReceivable());
            currency.setMinSpendAmount(currencyModel.getMinSpendAmount());
            currency.setMaxSpendAmount(currencyModel.getMaxSpendAmount());
            currencyRepository.save(currency);
            return currencyModel;
        }else{
            throw new RuntimeException(CurrencyConstants.CURRENCY_NOT_EXIST);
        }

    }

    @Override
    public void delete(CurrencyModel currencyModel) {
        Currency currency = currencyRepository.findByCurrency(currencyModel.getCurrency());
        if (currency == null) {
            throw new RuntimeException(CurrencyConstants.CURRENCY_NOT_EXIST);
        }
        currencyRepository.delete(currency);
    }

    @Override
    public List<CurrencyModel> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAllByOrderByCurrencyIdAsc();
        if(currencies.isEmpty()){
            throw new RuntimeException(CurrencyConstants.CURRENCIES_DOES_NOT_EXIST);
        }
        return CurrencyModel.mapEntityListIntoModelList(currencies);
    }

    @Override
    public void checkApplicationHasInitialCurrencies() {
//        5,EUR,true,5000,25,false,true
//        1,BTC,false,,,true,false
//        2,ETH,false,,,true,false
//        4,USD,true,5000,25,false,true

        List<Currency> currencies = currencyRepository.findAll();
        if(currencies.isEmpty()){
            CurrencyModel fiatCurrencyModel = new CurrencyModel();
            fiatCurrencyModel.setCurrency("EUR");
            fiatCurrencyModel.setIsFiatCurrency(Boolean.TRUE);
            fiatCurrencyModel.setSpendable(Boolean.TRUE);
            fiatCurrencyModel.setReceivable(Boolean.FALSE);
            fiatCurrencyModel.setMinSpendAmount(25);
            fiatCurrencyModel.setMaxSpendAmount(5000);

            currencyRepository.save(fiatCurrencyModel.toEntity());
            fiatCurrencyModel.setCurrency("USD");
            currencyRepository.save(fiatCurrencyModel.toEntity());

            CurrencyModel currencyModel = new CurrencyModel();
            currencyModel.setCurrency("BTC");
            currencyModel.setIsFiatCurrency(Boolean.FALSE);
            currencyModel.setSpendable(Boolean.FALSE);
            currencyModel.setReceivable(Boolean.TRUE);

            currencyRepository.save(currencyModel.toEntity());
            currencyModel.setCurrency("ETH");
            currencyRepository.save(currencyModel.toEntity());
        }
    }
}