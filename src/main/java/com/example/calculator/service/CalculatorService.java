package com.example.calculator.service.calculator;

import com.example.calculator.model.calculator.InputData;
import com.example.calculator.model.calculator.OPERATOR;
import com.example.calculator.model.calculator.OutputData;

import java.util.HashMap;
import java.util.Map;

public interface CalculatorService {
    Map<String, OPERATOR> map = new HashMap<>();
    OutputData calculate(InputData data, OPERATOR operator);
}
