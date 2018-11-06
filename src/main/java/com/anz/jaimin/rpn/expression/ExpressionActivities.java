package com.anz.jaimin.rpn.expression;

import com.anz.jaimin.rpn.PatternCalculator;
import com.anz.jaimin.rpn.exception.EvaluatorException;

public abstract class ExpressionActivities implements Expression {

    private final String arithmeticOperator;
    private final String arithmeticOperatorOpp;
    private final int minimumOperands;

    public ExpressionActivities(String airthmeticOperator, String arithmeticOperatorOpp, int minimumOperands) {
        this.arithmeticOperator = airthmeticOperator;
        this.arithmeticOperatorOpp = arithmeticOperatorOpp;
        this.minimumOperands = minimumOperands;
    }

    public String getArithmeticOperator() {
        return arithmeticOperator;
    }

    public String getArithmeticOperatorOpp() {
        return arithmeticOperatorOpp;
    }

    public int getMinimumOperands() {
        return minimumOperands;
    }

    public void undo(PatternCalculator patternCalculator) throws EvaluatorException {
        patternCalculator.undoLastInstruction();
    }

    public void clear(PatternCalculator patternCalculator) {
        patternCalculator.clearStacks();
    }
}
