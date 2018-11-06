package com.anz.jaimin.rpn;

import com.anz.jaimin.rpn.exception.EvaluatorException;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class RpnEvaluator {

    public static void main(String[] args) {
    	
        Console console = System.console();
    	
        // Verifying if console is available
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        PatternCalculator calculator = new PatternCalculator();
        System.out.println("Enter your expression, or 'exit' to quit");

        boolean switchOnCalculator = true;
        while (switchOnCalculator) {
            String inputString = console.readLine(": ");
        
            if ("exit".equals(inputString)) {
                switchOnCalculator = false;
            } else {
                try {
                    calculator.eval(inputString);
                } catch (EvaluatorException e) {
                    System.out.println(e.getMessage());
                }

                DecimalFormat fmt = new DecimalFormat("0.##########");
                Stack<Double> stack = calculator.getValuesStack();
                System.out.print("Stack: ");
                for (Double value : stack) {
                    System.out.print(fmt.format(value));
                    System.out.print(" ");
                }
                System.out.printf("%n");
            }
        }
    }
}
