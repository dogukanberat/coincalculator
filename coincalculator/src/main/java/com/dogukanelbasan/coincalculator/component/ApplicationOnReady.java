package com.dogukanelbasan.coincalculator.component;


import com.dogukanelbasan.coincalculator.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationOnReady {

    @Autowired
    CurrencyService currencyService;

    @EventListener(ApplicationReadyEvent.class)
    public void createSocketConnection() throws Exception {
        currencyService.checkApplicationHasInitialCurrencies();
    }

}


