package com.anz.jaimin.rpn;

import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.factory.OperationFactory;
import com.anz.jaimin.rpn.expression.ExpressionActivities;
import com.anz.jaimin.rpn.operations.NonOperators;

import java.util.Stack;

public class PatternCalculator {

    private Stack<Double> valuesStack = new Stack<Double>();
    private Stack<InputInstruction> instructionsStack = new Stack<InputInstruction>();
    private int currentTokenIndex = 0;

    private Double parseInputDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    /**
     * Processes a RPN string token
     *
     * @param token           RPN token
     * @param isUndoOperation indicates if the operation is an undo operation.
     * @throws EvaluatorException
     */
    private void processToken(String token, boolean isUndoOperation) throws EvaluatorException {
        Double value = parseInputDouble(token);
        if (value == null) {
            processOperator(token, isUndoOperation);
        } else {
            // it's a digit
            valuesStack.push(Double.parseDouble(token));
            if (!isUndoOperation) {
                instructionsStack.push(null);
            }
        }
    }

    /**
     * Executes an operation on the stack
     *
     * @param operatorString  RPN valid operator
     * @param isUndoOperation indicates if the operation is an undo operation.
     * @throws EvaluatorException
     */
    private void processOperator(String operatorString, boolean isUndoOperation) throws EvaluatorException {

        // check if there is an empty stack
        if (valuesStack.isEmpty()) {
            throw new EvaluatorException("stack empty, enter expression or 'exit' to quit");
        }

        // Factory pattern: Decide which operation to be executed
        OperationFactory operationFactory = new OperationFactory();
        // Interpreter pattern: Decides the operation and stores the action
        ExpressionActivities activities = (ExpressionActivities) operationFactory.getOperation(this,operatorString);

        if(!(activities instanceof NonOperators)){
            //activities.undo();
            if (activities.getMinimumOperands() > valuesStack.size()) {
                throwInvalidOperand(operatorString);
            }
            // getting operands
            Double firstOperand = valuesStack.pop();
            Double secondOperand = (activities.getMinimumOperands() > 1) ? valuesStack.pop() : null;

            // Based on the object created operation is performed
            Double result = activities.calculate(firstOperand, secondOperand);

            if (result != null) {
                valuesStack.push(result);
                if (!isUndoOperation) {
                    instructionsStack.push(new InputInstruction(activities, firstOperand));
                }
            }
        }
    }

    public void undoLastInstruction() throws EvaluatorException {
        if (instructionsStack.isEmpty()) {
            throw new EvaluatorException("no operations to undo");
        }

        InputInstruction lastInstruction = instructionsStack.pop();
        if (lastInstruction == null) {
            valuesStack.pop();
        } else {
            eval(lastInstruction.getReverseInstruction(), true);
        }
    }

    public void clearStacks() {
        valuesStack.clear();
        instructionsStack.clear();
    }

    private void throwInvalidOperand(String operator) throws EvaluatorException {
        throw new EvaluatorException(
                String.format("operator %s (position: %d): insufficient parameters", operator, currentTokenIndex));
    }

    /**
     * Returns the values valuesStack
     */
    public Stack<Double> getValuesStack() {
        return valuesStack;
    }

    /**
     * Helper method to return a specific item in the valuesStack
     *
     * @param index index of the element to return
     */
    public Double getStackItem(int index) {
        return valuesStack.get(index);
    }

    /**
     * Evals a RPN expression and pushes the result into the valuesStack
     *
     * @param input valid RPN expression
     * @throws EvaluatorException
     */
    public void eval(String input) throws EvaluatorException {
        eval(input, false);
    }

    /**
     * Evals a RPN expression and pushes the result into the valuesStack
     *
     * @param input           valid RPN expression
     * @param isUndoOperation indicates if the operation is an undo operation.
     *                        undo operations use the same evaluation functions as the standard ones
     *                        but they are not pushed into instructionsStack
     * @throws EvaluatorException
     */
    private void eval(String input, boolean isUndoOperation) throws EvaluatorException {
        if ((input != null) && (input.length() == 0)) {
            throw new EvaluatorException("Input cannot be null.");
        }
        currentTokenIndex = 0;
        String[] inputValues = input.split("\\s");
        for (String inValue : inputValues) {
            currentTokenIndex++;
            processToken(inValue, isUndoOperation);
        }
    }
}
