import com.anz.jaimin.rpn.PatternCalculator;
import com.anz.jaimin.rpn.exception.EvaluatorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatternCalculatorTest {

    @Test
    public void testExample1() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("5 2");
        assertEquals(5, calculator.getValuesStack().get(0), 0);
        assertEquals(2, calculator.getStackItem(1), 0);
    }

    @Test
    public void testExample2() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("2 sqrt");
        calculator.eval("clear 9 sqrt");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(3, calculator.getStackItem(0), 0);
    }

    @Test
    public void testExample3() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("5 2 -");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(3, calculator.getStackItem(0), 0);
        calculator.eval("3 -");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(0, calculator.getStackItem(0), 0);
    }

    @Test
    public void testExample4() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("5 4 3 2");
        assertEquals(4, calculator.getValuesStack().size());
        calculator.eval("undo undo *");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(20, calculator.getStackItem(0), 0);
        calculator.eval("5 *");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(100, calculator.getStackItem(0), 0);
        calculator.eval("undo");
        assertEquals(2, calculator.getValuesStack().size());
        assertEquals(20, calculator.getStackItem(0), 0);
        assertEquals(5, calculator.getStackItem(1), 0);
    }

    @Test
    public void testExample5() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("7 12 2 /");
        assertEquals(7, calculator.getStackItem(0), 0);
        assertEquals(6, calculator.getStackItem(1), 0);
        calculator.eval("*");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(42, calculator.getStackItem(0), 0);
        calculator.eval("4 /");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(10.5, calculator.getStackItem(0), 0);
    }

    @Test
    public void testExample6() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("1 2 3 4 5");
        assertEquals(5, calculator.getValuesStack().size());
        calculator.eval("*");
        assertEquals(4, calculator.getValuesStack().size());
        calculator.eval("clear 3 4 -");
        assertEquals(1, calculator.getValuesStack().size());
    }

    @Test
    public void testExample7() throws Exception {
        PatternCalculator calculator = new PatternCalculator();
        calculator.eval("1 2 3 4 5");
        assertEquals(5, calculator.getValuesStack().size());
        calculator.eval("* * * *");
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(120, calculator.getStackItem(0), 0);
    }

    @Test
    public void testExample8() {
        PatternCalculator calculator = new PatternCalculator();
        try {
            calculator.eval("1 2 3 * 5 + * * 6 5");
        } catch (EvaluatorException e) {
            assertEquals("operator * (position: 8): insufficient parameters", e.getMessage());
        }
        assertEquals(1, calculator.getValuesStack().size());
        assertEquals(11, calculator.getStackItem(0), 0);
    }
}
