package com.dogukanelbasan.coincalculator.controller;

import com.dogukanelbasan.coincalculator.model.CurrencyModel;
import com.dogukanelbasan.coincalculator.service.CurrencyService;
import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@RestController("currency")
@RequestMapping(value = "api/currency")
public class CurrencyController  extends BaseController {
    @Autowired
    private CurrencyService currencyService;


    @GetMapping(value = "/getCurrencyList")
    public ResponseEntity<List<CurrencyModel>> getAllCurrencies() {
        List<CurrencyModel> currencies = currencyService.getAllCurrencies();
        return responseEntity(currencies);

    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<CurrencyModel> create(@RequestBody CurrencyModel currencyModel) throws Exception {
        return responseEntity(currencyService.save(currencyModel));
    }


    @DeleteMapping("/deleteCurrency")
    public ResponseEntity<String> removeEndConversationReasonsTemplate(@NotEmpty @RequestBody CurrencyModel currencyModel) throws Exception {
        currencyService.delete(currencyModel);
        return responseEntity(CurrencyConstants.CURRENCY_DELETED);
    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CurrencyModel> update(@RequestBody CurrencyModel currencyModel) throws Exception {
        return responseEntity(currencyService.update(currencyModel));
    }
}
