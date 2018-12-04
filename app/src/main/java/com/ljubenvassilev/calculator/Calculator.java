package com.ljubenvassilev.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

final class Calculator {

    private static Calculator instance;
    private State state;
    private DecimalFormat df;

    private Calculator(){
        state = State.NONE;
        df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    static Calculator getInstance(){
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    private Operation operation = Operation.NONE;
    private Double first;
    private Double second;

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    void setOperation(Operation operation) {
        this.operation = operation;
    }

    void setFirst(double first) {
        this.first = first;
    }

    void setSecond(double second) {
        this.second = second;
    }

    String calculate() throws IncorrectInputException{
        double result;
        switch (operation){
            case ADD:
                result = this.first + this.second;
                break;
            case SUBTRACT:
                result = first - second;
                break;
            case MULTIPLY:
                result = first * second;
                break;
            case DIVIDE:
                if (second == 0) {
                    reset();
                    throw new IncorrectInputException();
                }
                result = first / second;
                break;
            case NONE:
            default:
                reset();
                throw new IncorrectInputException();
        }
        reset();
        if (result >= 999999999999d){
            throw new IncorrectInputException();
        }
        return df.format(result);
    }

    void reset(){
        first = null;
        second = null;
        operation = Operation.NONE;
        state = State.NONE;
    }

    enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE
    }

    enum State {
        ONE,
        NONE
    }
}
