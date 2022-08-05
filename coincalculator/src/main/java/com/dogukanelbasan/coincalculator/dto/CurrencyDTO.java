package com.dogukanelbasan.coincalculator.dto;

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
public class CurrencyDTO {
    private Long currencyId;
    private String currency;
    private Boolean spendable;
    private Boolean receivable;
    private Number amount;
    private Integer maxSpendAmount;
    private Integer minSpendAmount;
    private Boolean isFiatCurrency;
    public static CurrencyDTO toDTO(Currency currency) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrencyId(currency.getCurrencyId());
        currencyDTO.setCurrency(currency.getCurrency());
        currencyDTO.setReceivable(currency.getReceivable());
        currencyDTO.setSpendable(currency.getSpendable());
        currencyDTO.setMaxSpendAmount(currency.getMaxSpendAmount());
        currencyDTO.setMinSpendAmount(currency.getMinSpendAmount());
        currencyDTO.setIsFiatCurrency(currency.getIsFiatCurrency());
        return currencyDTO;

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

    public static List<CurrencyDTO> mapEntityListIntoDTOList(List<Currency> source) {
        if (source != null) {
            return source.stream().map(CurrencyDTO::toDTO).collect(Collectors.<CurrencyDTO> toList());
        } else {
            return null;
        }
    }
}


