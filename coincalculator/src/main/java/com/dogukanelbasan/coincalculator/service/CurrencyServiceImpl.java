package com.dogukanelbasan.coincalculator.service;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.entity.Currency;
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
    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        Currency currency = currencyRepository.findByCurrency(currencyDTO.getCurrency());
        if (currency != null) {
            throw new RuntimeException(CurrencyConstants.CURRENCY_ALREADY_EXIST);
        }
        Currency currency1 = currencyDTO.toEntity();
        currencyRepository.save(currency1);
        return currencyDTO;
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        Currency currency = currencyRepository.findByCurrency(currencyDTO.getCurrency());
        if (currency != null) {
            currency.setIsFiatCurrency(currencyDTO.getIsFiatCurrency());
            currency.setSpendable(currencyDTO.getSpendable());
            currency.setReceivable(currencyDTO.getReceivable());
            currency.setMinSpendAmount(currencyDTO.getMinSpendAmount());
            currency.setMaxSpendAmount(currencyDTO.getMaxSpendAmount());
            currencyRepository.save(currency);
            return currencyDTO;
        }else{
            throw new RuntimeException(CurrencyConstants.CURRENCY_NOT_EXIST);
        }

    }

    @Override
    public void delete(CurrencyDTO currencyDTO) {
        Currency currency = currencyRepository.findByCurrency(currencyDTO.getCurrency());
        if (currency == null) {
            throw new RuntimeException(CurrencyConstants.CURRENCY_NOT_EXIST);
        }
        currencyRepository.delete(currency);
    }

    @Override
    public List<CurrencyDTO> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAllByOrderByCurrencyIdAsc();
        if(currencies.isEmpty()){
            throw new RuntimeException(CurrencyConstants.CURRENCIES_DOES_NOT_EXIST);
        }
        return CurrencyDTO.mapEntityListIntoDTOList(currencies);
    }
}