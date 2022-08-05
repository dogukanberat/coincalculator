package com.dogukanelbasan.coincalculator.validation;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.entity.Currency;
import com.dogukanelbasan.coincalculator.enums.OrderType;
import com.dogukanelbasan.coincalculator.exception.CoinException;
import com.dogukanelbasan.coincalculator.repository.CurrencyRepository;
import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class CurrencyFiatValidatorImpl implements CurrencyFiatValidator {

    @Autowired
    CurrencyRepository currencyRepository;


    @Override
    public void isValid(CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO) {
        CurrencyDTO fiatCurrencyDto = fiatCurrencyToCryptoCurrencyDTO.getFiatCurrency();
        CurrencyDTO cryptoDto = fiatCurrencyToCryptoCurrencyDTO.getCrypto();
        Boolean hasError = false;
        Boolean hasCryptoCurrencyError = false;
        Boolean hasFiatCurrencyError = false;
        Map<String, Object> errorAttributes = new HashMap<>();

        if (fiatCurrencyDto == null || cryptoDto == null) {
            hasError = setErrorMessage(CurrencyConstants.NULL_MSG, errorAttributes);
        } else {
            boolean calculateWithFiatCurrency = OrderType.FIAT.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrderType());
            boolean calculateWithCrypto = OrderType.CRYPTO.value().equals(fiatCurrencyToCryptoCurrencyDTO.getOrderType());
            if (calculateWithFiatCurrency || calculateWithCrypto) {
                hasFiatCurrencyError = checkFiatCurrency(fiatCurrencyDto,errorAttributes);
                hasCryptoCurrencyError = checkCryptoCurrency(cryptoDto,errorAttributes);
            }else {
                hasError = setErrorMessage(CurrencyConstants.ORDER_TYPE_MSG, errorAttributes);
            }
        }
        if (hasError || hasFiatCurrencyError ||  hasCryptoCurrencyError) {
            Object messages = errorAttributes.get("messages");
            throw new CoinException(messages);
        }
    }

    public Boolean checkFiatCurrency(CurrencyDTO fiatCurrencyDto,Map<String, Object> errorAttributes){
        Currency currency = currencyRepository.findByCurrency(fiatCurrencyDto.getCurrency());
        if (currency != null) {
            CurrencyDTO fiatCurrency = CurrencyDTO.toDTO(currency);

            if (fiatCurrency.getMinSpendAmount() != null) {
                if (fiatCurrency.getSpendable()) {
                    return setErrorMessage(CurrencyConstants.NOT_SPENDABLE_MESSAGE, errorAttributes);
                }
                if (fiatCurrencyDto.getAmount().doubleValue() < fiatCurrency.getMinSpendAmount()) {
                    return setErrorMessage(CurrencyConstants.SMALL_PRICE_MSG, errorAttributes);
                }
                if (fiatCurrencyDto.getAmount().doubleValue() > fiatCurrency.getMaxSpendAmount()) {
                    return setErrorMessage(CurrencyConstants.BIG_PRICE_MSG, errorAttributes);
                }
            } else {
                return setErrorMessage(CurrencyConstants.INVALID_CURRENCY_MSG, errorAttributes);
            }
        } else {
            return setErrorMessage(CurrencyConstants.INVALID_CURRENCY_MSG, errorAttributes);
        }

        return false;
    }
    public Boolean checkCryptoCurrency(CurrencyDTO cryptoCurrencyData,Map<String, Object> errorAttributes){
        Currency cryptoCurrency = currencyRepository.findByCurrency(cryptoCurrencyData.getCurrency());
        if (cryptoCurrency != null) {
            CurrencyDTO cryptoCurrencyModel = CurrencyDTO.toDTO(cryptoCurrency);
            if (cryptoCurrencyModel.getReceivable()) {
               return setErrorMessage(CurrencyConstants.NOT_RECEIVABLE_MESSAGE, errorAttributes);
            }
        } else {
            return setErrorMessage(CurrencyConstants.NOT_RECEIVABLE_MESSAGE, errorAttributes);
        }
        return false;
    }

    public Boolean setErrorMessage(String errorMessage, Map<String, Object> errorAttributes) {
        ArrayList<String> errorMessageList = (ArrayList) errorAttributes.get("messages");
        if (errorMessageList == null) {
            errorMessageList = new ArrayList<String>();
        }
        errorMessageList.add(errorMessage);
        errorAttributes.put("messages", errorMessageList);
        return true;
    }
}

