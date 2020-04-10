package com.example.NBRBApi.model.service;

import com.example.NBRBApi.model.dao.jdbc.RateShortJdbc;
import com.example.NBRBApi.model.domain.Rate;
import com.example.NBRBApi.model.domain.RateShort;
import com.example.NBRBApi.model.dao.DataJpa.RateRepo;
import com.example.NBRBApi.model.dao.DataJpa.RateShortRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RateShortService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RateShortService.class.getName());

    @Autowired
    private RateShortRepo rateShortRepo;
    @Autowired
    private NbrbExchange nbrbExchange;
    @Autowired
    private RateRepo rateRepo;
    @Autowired
    private RateShortJdbc rateShortJdbc;

    public void syncRateShort() {
        LOGGER.info("Synchronization with NBRB");
        List<Rate> rates = (List<Rate>) rateRepo.findAll();
        LocalDate startDate = LocalDate.of(2020, 3, 1);
        LocalDate endDate = LocalDate.now();

        for (Rate i : rates) {
            List<RateShort> rateShortList = nbrbExchange.getRateShorts(i.curID, startDate, endDate);
            rateShortRepo.saveAll(rateShortList);
        }
    }

    public List<RateShort> getHistory(String curAbbreviation) {
        LOGGER.info("Get history " + curAbbreviation);

        return rateShortRepo.findByCurID(rateRepo.findByCurAbbreviation(curAbbreviation).curID);
        //jdbc
        //return rateShortJdbc.findByCurId(rateRepo.findByCurAbbreviation(curAbbreviation).curID);
    }

}
