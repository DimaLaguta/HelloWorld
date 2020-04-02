package com.example.NBRBApi.service;

import com.example.NBRBApi.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private NbrbExchange nbrbExchange;
    private List<Currency> currencies;

    public CurrencyService() {

    }

    @PostConstruct
    private void postConstructor() {
        currencies = nbrbExchange.getAllCurrencies();
    }

    public boolean isValid(String curAbbreviation) {
        return currencies.stream().anyMatch(x -> x.curAbbreviation.equals(curAbbreviation));
    }

}
