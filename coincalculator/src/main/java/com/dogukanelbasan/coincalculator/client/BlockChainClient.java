package com.dogukanelbasan.coincalculator.client;

import com.dogukanelbasan.coincalculator.dto.CurrencyResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "blockchain", url = "https://api.blockchain.com/v3/exchange")
public interface BlockChainClient {

    @GetMapping("/tickers/{currency}")
    public CurrencyResponseDTO getCurrency(@PathVariable("currency") String currency);


}
