package com.example.calculator.service;

import com.example.calculator.model.InputData;
import com.example.calculator.model.Operator;
import com.example.calculator.model.OutputData;

import java.util.HashMap;
import java.util.Map;

public interface CalculatorService {
    Map<String, Operator> map = new HashMap<>();
    OutputData calculate(InputData data, Operator operator);
}
