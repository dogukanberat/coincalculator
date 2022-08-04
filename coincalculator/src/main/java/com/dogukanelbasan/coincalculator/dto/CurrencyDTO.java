package com.dogukanelbasan.coincalculator.dto;

import com.dogukanelbasan.coincalculator.entity.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public static CurrencyDTO toDTO(Currency currency) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrencyId(currency.getCurrencyId());
        currencyDTO.setCurrency(currency.getCurrency());
        currencyDTO.setReceivable(currency.getReceivable());
        currencyDTO.setSpendable(currency.getSpendable());
        return currencyDTO;

    }

    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setCurrencyId(this.getCurrencyId());
        currency.setCurrency(this.getCurrency());
        currency.setReceivable(this.getReceivable());
        currency.setSpendable(this.getSpendable());
        return currency;
    }
}


