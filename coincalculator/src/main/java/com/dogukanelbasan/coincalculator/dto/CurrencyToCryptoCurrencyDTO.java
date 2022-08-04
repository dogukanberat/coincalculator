package com.dogukanelbasan.coincalculator.dto;

import com.dogukanelbasan.coincalculator.validation.CurrencyFiatConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CurrencyFiatConstraint()
public class CurrencyToCryptoCurrencyDTO implements Serializable {
    private CurrencyDTO crypto;
    private CurrencyDTO fiatCurrency;
    private String order_type;
}
