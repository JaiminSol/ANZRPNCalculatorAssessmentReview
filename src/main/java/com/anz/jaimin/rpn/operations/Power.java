package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

import static java.lang.Math.pow;

public class Power extends ExpressionActivities implements Expression {

    public Power() {
        super("pow", "sqrt", 1);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
        return pow(firstOperand, 2.0);
    }
}
