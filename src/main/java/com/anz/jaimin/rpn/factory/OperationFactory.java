package com.anz.jaimin.rpn.factory;

import com.anz.jaimin.rpn.PatternCalculator;
import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.expression.ExpressionActivities;
import com.anz.jaimin.rpn.operations.Addition;
import com.anz.jaimin.rpn.operations.Division;
import com.anz.jaimin.rpn.expression.Expression;
import com.anz.jaimin.rpn.operations.Multiplication;
import com.anz.jaimin.rpn.operations.NonOperators;
import com.anz.jaimin.rpn.operations.Power;
import com.anz.jaimin.rpn.operations.SquareRoot;
import com.anz.jaimin.rpn.operations.Subtraction;

public class OperationFactory {
	
	
    public Expression getOperation(PatternCalculator patternCalculator, String operationActivity) throws EvaluatorException {
        ExpressionActivities activities = new NonOperators();
        switch (operationActivity) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            case "/":
                return new Division();
            case "sqrt":
                return new SquareRoot();
            case "pow":
                return new Power();
            case "undo":
                activities.undo(patternCalculator);
                return activities;
            case "clear":
                activities.clear(patternCalculator);
                return activities;
            default:
            	throw new EvaluatorException(
            			String.format("invalid operator: %s", operationActivity));
        }
    }

}
