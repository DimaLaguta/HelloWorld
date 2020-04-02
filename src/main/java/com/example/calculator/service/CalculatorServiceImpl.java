package com.example.calculator.service.calculator;

import com.example.calculator.model.calculator.InputData;
import com.example.calculator.model.calculator.OPERATOR;
import com.example.calculator.model.calculator.OutputData;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    public CalculatorServiceImpl() {
        map.put("+", OPERATOR.ADDITION);
        map.put("-", OPERATOR.SUBTRACTION);
        map.put("*", OPERATOR.MULTIPLICATION);
        map.put("/", OPERATOR.DIVISION);
    }

    @Override
    public OutputData calculate(InputData data, OPERATOR operator) {
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
                    throw new IllegalArgumentException("Деление на 0");
                }
            default:
                throw new IllegalArgumentException("Некорректная операция");
        }
    }
}
