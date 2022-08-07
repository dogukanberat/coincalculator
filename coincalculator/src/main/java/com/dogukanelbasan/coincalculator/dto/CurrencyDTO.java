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
    private String currency;
    private Number amount;


}


