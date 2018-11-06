package com.anz.jaimin.rpn.operations;

import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

public class Division extends ExpressionActivities implements Expression {

    public Division() {
        super("/", "*", 2);
    }

    @Override
    public Double calculate(Double firstOperand, Double secondOperand) throws EvaluatorException {
        if (firstOperand == 0)
            throw new EvaluatorException("Cannot divide by 0.");
        return secondOperand / firstOperand;
    }
}
