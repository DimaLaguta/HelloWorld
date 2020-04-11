package com.example.NBRBApi.model.service;

import com.example.NBRBApi.model.dao.DataJpa.RateRepo;
import com.example.NBRBApi.model.dao.jdbc.RateJdbc;
import com.example.NBRBApi.model.domain.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RateService.class.getName());

    @Autowired
    private NbrbExchange nbrbExchange;
    @Autowired
    private RateRepo rateRepo;

    @Autowired
    private RateJdbc rateJdbc;

    public RateService() {
    }

    public void syncRate() {
        LOGGER.info("Synchronization with NBRB");
        try {
            rateRepo.saveAll(nbrbExchange.getAllRates());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public boolean isValid(String curAbbreviation) {
        Rate resRate = null;
        resRate = rateRepo.findByCurAbbreviation(curAbbreviation);

        return resRate != null;
    }

    public List<Rate> getAllRates() {
        LOGGER.info("Get all rates");
        List<Rate> rates = new ArrayList<>();
        try {
            rates = (List<Rate>) rateRepo.findAll();
            //jdbc
            //rates = rateJdbc.getAllRates();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rates;
    }

    public Rate getRate(String curAbbreviation) {
        LOGGER.info("Get " + curAbbreviation + " rate");

        return rateRepo.findByCurAbbreviation(curAbbreviation);
        //jdbc
        //return rateJdbc.findByCurAbbreviation(curAbbreviation);
    }

    public List<Rate> getRateForPeriod(String curAbbreviation, LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Get " + curAbbreviation + " rate from " + startDate.toString() + " to " + endDate.toString());

        List<LocalDate> totalDate = datesOnPeriod(startDate, endDate);
        return totalDate.stream().map(x -> getRate(curAbbreviation, x)).collect(Collectors.toList());
    }

    private List<LocalDate> datesOnPeriod(LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Calculate date on period ");
        List<LocalDate> resultDates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            resultDates.add(startDate);
            startDate = startDate.plusDays(1);
        }
        return resultDates;
    }

    private Rate getRate(String curAbbreviation, LocalDate date) {
        if (curAbbreviation == null || date == null) {
            LOGGER.warn("Input parameters are null");
        }

        Rate rate = new Rate();
        try {
            //rate = nbrbExchange.getRate(curAbbreviation, date);
            rate =rateJdbc.getRateByCurAbbreviationAndDate(curAbbreviation,date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rate;
    }
}
