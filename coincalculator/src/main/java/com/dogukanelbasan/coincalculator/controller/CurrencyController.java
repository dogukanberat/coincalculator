package com.dogukanelbasan.coincalculator.controller;

import com.dogukanelbasan.coincalculator.dto.CurrencyDTO;
import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.service.CalculatorService;
import com.dogukanelbasan.coincalculator.service.CurrencyService;
import com.dogukanelbasan.coincalculator.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("currency")
@RequestMapping(value = "api/currency")
public class CurrencyController  extends BaseController {
    @Autowired
    private CurrencyService currencyService;


    @GetMapping(value = "/getCurrencyList")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<CurrencyDTO> currencies = currencyService.getAllCurrencies();
        return responseEntity(currencies);

    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<CurrencyDTO> create(@RequestBody CurrencyDTO currencyDTO) throws Exception {
        return responseEntity(currencyService.save(currencyDTO));
    }


    @DeleteMapping("/deleteCurrency")
    public ResponseEntity<String> removeEndConversationReasonsTemplate(@NotEmpty @RequestBody CurrencyDTO currencyDTO) throws Exception {
        currencyService.delete(currencyDTO);
        return responseEntity(Constants.CURRENCY_DELETED);
    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CurrencyDTO> update(@RequestBody CurrencyDTO currencyDTO) throws Exception {
        return responseEntity(currencyService.update(currencyDTO));
    }
}
