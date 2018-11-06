package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

public class NonOperators extends ExpressionActivities implements Expression {
    public NonOperators() {
        super("nonOperator", null, 0);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) {
        return null;
    }
}