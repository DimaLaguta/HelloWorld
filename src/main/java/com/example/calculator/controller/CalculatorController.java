package com.example.calculator.controller;

import com.example.NBRBApi.controller.RateController;
import com.example.calculator.model.InputData;
import com.example.calculator.model.OutputData;
import com.example.calculator.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class.getName());


    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<OutputData> getResultGET(@RequestParam(value = "firstValue", required = true) Double firstValue,
                                                   @RequestParam(value = "secondValue", required = true) Double secondValue,
                                                   @RequestParam(value = "operator", required = true) String operator) {
        if (firstValue == null || secondValue == null || operator == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        InputData inputData = new InputData(firstValue, secondValue);
        OutputData outputData;
        try {
            outputData = calculatorService.calculate(inputData, CalculatorService.map.get(operator));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputData, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<OutputData> getResultPOST(@RequestBody InputData inputData,
                                                    @RequestParam(value = "Operator", required = true) String operator) {
        if (inputData == null || operator == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OutputData outputData;
        try {
            outputData = calculatorService.calculate(inputData, CalculatorService.map.get(operator));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(outputData, HttpStatus.OK);
    }
}
