package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

public class Subtraction extends ExpressionActivities implements Expression {

    public Subtraction() {
        super("-", "+", 2);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
        return secondOperand - firstOperand;
    }
}
