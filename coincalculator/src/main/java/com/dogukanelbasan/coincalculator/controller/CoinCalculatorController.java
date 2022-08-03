package com.dogukanelbasan.coincalculator.controller;

import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("orders")
@RequestMapping(value = "api/orders")
public class CoinCalculatorController extends BaseController {

	
	@Autowired
	private CalculatorService calculatorService;


	@PostMapping(value = "/prices",produces = "application/json", consumes = "application/json")
	public @ResponseBody  ResponseEntity<Object> prices(@Nullable @RequestBody CurrencyToCryptoCurrencyDTO currencyToCryptoCurrencyDTO) throws Exception {
		return  responseEntity(calculatorService.prices(currencyToCryptoCurrencyDTO));
	}


}
