package com.example.NBRBApi.controller;

import com.example.NBRBApi.model.domain.Rate;
import com.example.NBRBApi.model.domain.RateShort;
import com.example.NBRBApi.model.service.RateService;
import com.example.NBRBApi.model.service.RateShortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/rates")
public class RateController {
    @Autowired
    private RateService rateService;
    @Autowired
    private RateShortService rateShortService;

    public static final Logger LOGGER = LoggerFactory.getLogger(RateController.class.getName());

    @GetMapping()
    public ResponseEntity<List<Rate>> getAllRates() {
        List<Rate> rates = rateService.getAllRates();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @GetMapping(value = "/{curAbbreviation}")
    public ResponseEntity<List<Rate>> getRateByAbbreviation(@PathVariable String curAbbreviation,
                                                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        if (!rateService.isValid(curAbbreviation)) {
            LOGGER.error("curAbbreviation is invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (startDate != null && endDate != null) {
            return new ResponseEntity<>(rateService.getRateForPeriod(curAbbreviation, startDate, endDate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Arrays.asList(rateService.getRate(curAbbreviation)), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/history/{curAbbreviation}")
    public ResponseEntity<List<RateShort>> getHistory(@PathVariable String curAbbreviation) {

        if (!rateService.isValid(curAbbreviation)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<RateShort> result = rateShortService.getHistory(curAbbreviation);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping(value = "/sync")
    public ResponseEntity syncWithNbrb() {
        rateService.syncRate();
        rateShortService.syncRateShort();
        return new ResponseEntity(HttpStatus.OK);
    }
}
