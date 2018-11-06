package com.anz.jaimin.rpn.expression;

import com.anz.jaimin.rpn.exception.EvaluatorException;

public interface Expression {
    Double calculate(Double firstOperand, Double secondOperand) throws EvaluatorException;
}