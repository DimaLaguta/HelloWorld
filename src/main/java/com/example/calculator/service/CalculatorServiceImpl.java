package com.example.calculator.service;

import com.example.calculator.model.InputData;
import com.example.calculator.model.Operator;
import com.example.calculator.model.OutputData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CalculatorServiceImpl.class.getName());

    public CalculatorServiceImpl() {
        map.put("+", Operator.ADDITION);
        map.put("-", Operator.SUBTRACTION);
        map.put("*", Operator.MULTIPLICATION);
        map.put("/", Operator.DIVISION);
    }

    @Override
    public OutputData calculate(InputData data, Operator operator) {
        switch (operator) {
            case ADDITION:
                return new OutputData(data.getFirstValue() + data.getSecondValue());
            case SUBTRACTION:
                return new OutputData(data.getFirstValue() - data.getSecondValue());
            case MULTIPLICATION:
                return new OutputData(data.getFirstValue() * data.getSecondValue());
            case DIVISION:
                if (data.getSecondValue() != 0) {
                    return new OutputData(data.getFirstValue() / data.getSecondValue());
                } else {
                    LOGGER.warn("data.getSecondValue == " + data.getSecondValue());
                    throw new IllegalArgumentException("Деление на 0");
                }
            default:
                throw new IllegalArgumentException("Некорректная операция");
        }
    }
}
