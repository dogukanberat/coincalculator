package com.dogukanelbasan.coincalculator.model;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.entity.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyModel {
    private Long currencyId;
    private String currency;
    private Boolean spendable;
    private Boolean receivable;
    private Number amount;
    private Integer maxSpendAmount;
    private Integer minSpendAmount;
    private Boolean isFiatCurrency;

    public static CurrencyModel toModel(Currency currency) {
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setCurrencyId(currency.getCurrencyId());
        currencyModel.setCurrency(currency.getCurrency());
        currencyModel.setReceivable(currency.getReceivable());
        currencyModel.setSpendable(currency.getSpendable());
        currencyModel.setMaxSpendAmount(currency.getMaxSpendAmount());
        currencyModel.setMinSpendAmount(currency.getMinSpendAmount());
        currencyModel.setIsFiatCurrency(currency.getIsFiatCurrency());
        return currencyModel;

    }

    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setCurrencyId(this.getCurrencyId());
        currency.setCurrency(this.getCurrency());
        currency.setReceivable(this.getReceivable());
        currency.setSpendable(this.getSpendable());
        currency.setMaxSpendAmount(this.getMaxSpendAmount());
        currency.setMinSpendAmount(this.getMinSpendAmount());
        currency.setIsFiatCurrency(this.getIsFiatCurrency());
        return currency;
    }

    public static List<CurrencyModel> mapEntityListIntoModelList(List<Currency> source) {
        if (source != null) {
            return source.stream().map(CurrencyModel::toModel).collect(Collectors.<CurrencyModel> toList());
        } else {
            return null;
        }
    }
}
