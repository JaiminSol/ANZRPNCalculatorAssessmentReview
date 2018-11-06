import com.anz.jaimin.rpn.InputInstruction;
import com.anz.jaimin.rpn.exception.EvaluatorException;
import com.anz.jaimin.rpn.expression.ExpressionActivities;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class InstructionTest {

    @Test
    public void testReverseOneOperandInstruction() throws EvaluatorException {

        ExpressionActivities mockOperator = Mockito.mock(ExpressionActivities.class);
        when(mockOperator.getMinimumOperands()).thenReturn(1);
        when(mockOperator.getArithmeticOperatorOpp()).thenReturn("sqrt");

        Random r = new Random();
        InputInstruction instruction = new InputInstruction(mockOperator, r.nextDouble());

        assertEquals(String.format("%s", mockOperator.getArithmeticOperatorOpp()), instruction.getReverseInstruction());
    }

    @Test
    public void testReverseTwoOperandInstruction() throws EvaluatorException {

        ExpressionActivities mockOperator = Mockito.mock(ExpressionActivities.class);
        when(mockOperator.getMinimumOperands()).thenReturn(2);
        when(mockOperator.getArithmeticOperatorOpp()).thenReturn("-");

        Random r = new Random();
        double value = r.nextDouble();
        InputInstruction instruction = new InputInstruction(mockOperator, value);

        assertEquals(
                String.format("%f %s %f", value, mockOperator.getArithmeticOperatorOpp(), value),
                instruction.getReverseInstruction()
        );
    }

    @Test(expected = EvaluatorException.class)
    public void testInvalidOperandsNumber() throws EvaluatorException {
        ExpressionActivities mockOperator = Mockito.mock(ExpressionActivities.class);
        when(mockOperator.getMinimumOperands()).thenReturn(0);

        Random r = new Random();
        InputInstruction instruction = new InputInstruction(mockOperator, r.nextDouble());
        instruction.getReverseInstruction();
    }
}
