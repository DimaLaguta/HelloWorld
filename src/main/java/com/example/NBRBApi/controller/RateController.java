package com.example.NBRBApi.controller;

import com.example.NBRBApi.model.Rate;
import com.example.NBRBApi.service.CurrencyService;
import com.example.NBRBApi.service.RateService;
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
    private CurrencyService currencyService;

    @GetMapping(value = "")
    public ResponseEntity<List<Rate>> getAllRates() {

        List<Rate> rates = rateService.getAllRates();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @GetMapping(value = "/{curAbbreviation}")
    public ResponseEntity<List<Rate>> getRateByAbbreviation(@PathVariable String curAbbreviation,
                                                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        if (!currencyService.isValid(curAbbreviation)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (startDate != null && endDate != null) {
            return new ResponseEntity<>(rateService.getRateForPeriod(curAbbreviation, startDate, endDate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Arrays.asList(rateService.getRate(curAbbreviation)), HttpStatus.OK);
        }
    }
}
