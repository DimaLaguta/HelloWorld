package com.example.NBRBApi.service;

import com.example.NBRBApi.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    @Autowired
    private NbrbExchange nbrbExchange;

    public RateService() {
    }

    public List<Rate> getAllRates() {
        return nbrbExchange.getAllRates();
    }

    public Rate getRate(String curAbbreviation) {
        return nbrbExchange.getRate(curAbbreviation);
    }

    public List<Rate> getRateForPeriod(String curAbbreviation, LocalDate startDate, LocalDate endDate) {
        List<LocalDate> totalDate = datesOnPeriod(startDate, endDate);
        return totalDate.stream().map(x -> getRate(curAbbreviation, x)).collect(Collectors.toList());
    }

    private List<LocalDate> datesOnPeriod(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> resultDates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            resultDates.add(startDate);
            startDate = startDate.plusDays(1);
        }
        return resultDates;
    }

    private Rate getRate(String curAbbreviation, LocalDate date) {
        return nbrbExchange.getRate(curAbbreviation, date);
    }

}
