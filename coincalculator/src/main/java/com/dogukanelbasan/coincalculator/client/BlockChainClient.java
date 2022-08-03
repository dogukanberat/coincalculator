package com.dogukanelbasan.coincalculator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "blockchain", url = "https://api.blockchain.com/v3/exchange")
public interface BlockChainClient {

    @GetMapping("/tickers/{currency}")
    public Object getCurrency(@PathVariable("currency") String currency);


}
