package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

public class Clear extends ExpressionActivities implements Expression {
    public Clear() {
        super("clear", null, 0);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
        return null;
    }
}