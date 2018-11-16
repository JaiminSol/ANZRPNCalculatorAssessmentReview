package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

import static java.lang.Math.sqrt;

public class SquareRoot extends ExpressionActivities implements Expression {

    public SquareRoot() {
        super("sqrt", "pow", 1);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws EvaluatorException {
    	if (firstOperand < 0)
            throw new EvaluatorException("Invalid input.");
        return sqrt(firstOperand);
    }
}