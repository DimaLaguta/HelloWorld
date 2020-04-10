package com.example.NBRBApi.model.service;

import com.example.NBRBApi.model.dao.DataJpa.CurrencyRepo;
import com.example.NBRBApi.model.dao.jdbc.CurrencyJdbc;
import com.example.NBRBApi.model.domain.Currency;
import com.example.NBRBApi.model.domain.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private NbrbExchange nbrbExchange;
    @Autowired
    private CurrencyJdbc currencyJdbc;

    public CurrencyService() {
    }

    public boolean isValid(String curAbbreviation) {
        List<Currency> currency = currencyRepo.findByCurAbbreviation(curAbbreviation);
        //jdbc
        //List<CurrencyDelete> currency = currencyJdbc.findByCurAbbreviation(curAbbreviation);

        return currency.size() > 0;
    }

    public void syncCurrency() {
        List<Currency> currencies = nbrbExchange.getAllCurrencies();

        currencyRepo.saveAll(currencies);
    }
}
