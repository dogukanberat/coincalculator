package com.dogukanelbasan.coincalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponseDTO {
    private String symbol;
    private String price_24h;
    private String volume_24h;
    private String last_trade_price;
}
