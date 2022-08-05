package com.dogukanelbasan.coincalculator.controller;

import com.dogukanelbasan.coincalculator.dto.CurrencyToCryptoCurrencyDTO;
import com.dogukanelbasan.coincalculator.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController("orders")
@RequestMapping(value = "api/orders")
public class CoinCalculatorController extends BaseController {


	@Autowired
	private CalculatorService calculatorService;


	@PostMapping(value = "/prices", produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<CurrencyToCryptoCurrencyDTO> prices(@RequestBody CurrencyToCryptoCurrencyDTO fiatCurrencyToCryptoCurrencyDTO, Errors errors) throws Exception {
		if (errors.hasErrors()) {
			List<String> errorList = new ArrayList<>();
			for (ObjectError e : errors.getAllErrors()) {
				errorList.add(e.getDefaultMessage());
			}
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errors", errorList);
			return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
		}
		CurrencyToCryptoCurrencyDTO prices = calculatorService.prices(fiatCurrencyToCryptoCurrencyDTO);
		return responseEntity(prices);
	}


}
