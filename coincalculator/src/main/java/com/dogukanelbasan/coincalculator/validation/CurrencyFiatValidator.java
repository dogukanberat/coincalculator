package com.dogukanelbasan.coincalculator.validation;

import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;

import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public interface CurrencyFiatValidator {

    void isValid(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO);

}
