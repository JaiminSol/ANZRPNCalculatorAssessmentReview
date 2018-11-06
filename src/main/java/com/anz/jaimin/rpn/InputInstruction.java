package com.anz.jaimin.rpn;

import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.expression.ExpressionActivities;

public class InputInstruction {
    ExpressionActivities expressionActivities;
    Double value;

    public InputInstruction(ExpressionActivities expressionActivities, Double value) {
        this.expressionActivities = expressionActivities;
        this.value = value;
}

    public String getReverseInstruction() throws EvaluatorException {
        if (expressionActivities.getMinimumOperands() < 1)
            throw new EvaluatorException(String.format("invalid operation for operator %s", expressionActivities.getArithmeticOperatorOpp()));

        return (expressionActivities.getMinimumOperands() < 2) ?
                String.format("%s", expressionActivities.getArithmeticOperatorOpp()) :
                String.format("%f %s %f", value, expressionActivities.getArithmeticOperatorOpp(), value);
    }
}
