package com.dogukanelbasan.coincalculator.validation;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.utils.Constants;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CurrencyFiatValidator implements
        ConstraintValidator<CurrencyFiatConstraint, CurrencyToCryptoCurrencyDTO> {


    @Override
    public void initialize(CurrencyFiatConstraint fiatCurrencyToCryptoCurrencyDTO) {
    }

    @Override
    public boolean isValid(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO, ConstraintValidatorContext cxt) {
        Boolean hasError = false;
        CurrencyDTO fiatCurrencyDto = fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency();
        CurrencyDTO cryptoDto = fiatCurrencyToCryptoCurrencyDTO.getCrypto();
        if (fiatCurrencyDto == null || cryptoDto == null) {

            hasError = setErrorMessage(cxt, Constants.NULL_MSG);
        } else {
            if (Constants.ORDER_TYPE.BUY.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type())) {
                if (fiatCurrencyDto.getAmount().doubleValue() < 25) {
                    hasError = setErrorMessage(cxt, Constants.SMALL_PRICE_MSG);
                }
                if (fiatCurrencyDto.getAmount().doubleValue() > 5000) {
                    hasError = setErrorMessage(cxt, Constants.BIG_PRICE_MSG);
                }
            } else if (Constants.ORDER_TYPE.SELL.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrder_type())) {

            } else {
                hasError = setErrorMessage(cxt, Constants.ORDER_TYPE_MSG);
            }

            if (!Pattern.matches(Constants.COIN_TYPES, cryptoDto.getCurrency())) {
                hasError = setErrorMessage(cxt, Constants.INVALID_COIN_MSG);
            }
            if (!Pattern.matches(Constants.CURRENCY_TYPES, fiatCurrencyDto.getCurrency())) {
                hasError = setErrorMessage(cxt, Constants.INVALID_CURRENCY_MSG);

            }
        }

        if (hasError) {
            return false;
        } else {
            return true;
        }

    }


    public Boolean setErrorMessage(ConstraintValidatorContext cxt, String msg) {
        cxt.disableDefaultConstraintViolation();
        cxt
                .buildConstraintViolationWithTemplate(msg)
                .addConstraintViolation();
        return true;
    }
}
