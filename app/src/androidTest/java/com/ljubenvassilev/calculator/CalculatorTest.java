package com.ljubenvassilev.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp(){
        calculator = Calculator.getInstance();
    }

    @Test
    public void instanceIsNotNull(){
        assertNotNull(calculator);
    }

    @Test
    public void addWorks() throws IncorrectInputException{
        calculator.setFirst(2.9d);
        calculator.setSecond(2.1d);
        calculator.setOperation(Calculator.Operation.ADD);
        String value = calculator.calculate();
        assertEquals("5", value);
    }

    @Test
    public void addNotNull() throws IncorrectInputException{
        calculator.setFirst(2.9d);
        calculator.setSecond(2.1d);
        calculator.setOperation(Calculator.Operation.ADD);
        String value = calculator.calculate();
        assertNotNull(value);
    }

    @Test
    public void multiplyWorks() throws IncorrectInputException{
        calculator.setFirst(2.5d);
        calculator.setSecond(3.1d);
        calculator.setOperation(Calculator.Operation.MULTIPLY);
        String value = calculator.calculate();
        assertEquals("7.75", value);
    }

    @Test
    public void multiplyNotNull() throws IncorrectInputException{
        calculator.setFirst(2.5d);
        calculator.setSecond(3.1d);
        calculator.setOperation(Calculator.Operation.MULTIPLY);
        String value = calculator.calculate();
        assertNotNull(value);
    }

    @Test
    public void subtractWorks() throws IncorrectInputException{
        calculator.setFirst(2.5d);
        calculator.setSecond(3.1d);
        calculator.setOperation(Calculator.Operation.SUBTRACT);
        String value = calculator.calculate();
        assertEquals("-0.6", value);
    }

    @Test
    public void subtractNotNull() throws IncorrectInputException{
        calculator.setFirst(2.5d);
        calculator.setSecond(3.1d);
        calculator.setOperation(Calculator.Operation.SUBTRACT);
        String value = calculator.calculate();
        assertNotNull(value);
    }

    @Test
    public void divideWorks() throws IncorrectInputException{
        calculator.setFirst(7.5d);
        calculator.setSecond(2.0d);
        calculator.setOperation(Calculator.Operation.DIVIDE);
        String value = calculator.calculate();
        assertEquals("3.75", value);
    }

    @Test
    public void divideNotNull() throws IncorrectInputException{
        calculator.setFirst(7.5d);
        calculator.setSecond(2.0d);
        calculator.setOperation(Calculator.Operation.DIVIDE);
        String value = calculator.calculate();
        assertNotNull(value);
    }

    @Test(expected = IncorrectInputException.class)
    public void divideByZeroThrows() throws IncorrectInputException{
        calculator.setFirst(7.5d);
        calculator.setSecond(0d);
        calculator.setOperation(Calculator.Operation.DIVIDE);
        calculator.calculate();
    }

    @Test
    public void resetWorksForState() throws IncorrectInputException{
        calculator.setFirst(7.5d);
        calculator.setSecond(2.0d);
        calculator.setOperation(Calculator.Operation.DIVIDE);
        calculator.calculate();
        calculator.reset();
        assertEquals(Calculator.State.NONE, calculator.getState());
    }
}
