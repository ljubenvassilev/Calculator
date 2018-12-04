package com.ljubenvassilev.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public final class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private TextView screen;
    private TextView sign;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        sign = findViewById(R.id.sign);
        calculator = Calculator.getInstance();
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.subtract).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.equals).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.delete).setOnLongClickListener(this);
    }

    private void setOperation(Calculator.Operation operation){
        switch (calculator.getState()){
            case NONE:
                calculator.setOperation(operation);
                calculator.setFirst(Double.parseDouble(screen.getText().toString()));
                screen.setText(R.string.default_screen_zero);
                calculator.setState(Calculator.State.ONE);
                break;
            case ONE:
                calculator.setSecond(Double.parseDouble(screen.getText().toString()));
                calculate();
                calculator.setOperation(operation);
        }
        switch (operation){
            case ADD:
                sign.setText("+");
                break;
            case SUBTRACT:
                sign.setText("-");
                break;
            case MULTIPLY:
                sign.setText("*");
                break;
            case DIVIDE:
                sign.setText("/");
                break;
            default:
                sign.setText("");
        }
    }

    private void calculate() {
        try {
            String result = calculator.calculate();
            screen.setText(result);
            sign.setText("");
            calculator.setFirst(Double.parseDouble(result));
        } catch (IncorrectInputException e) {
            screen.setText(R.string.error);
            calculator.reset();
        } finally {
            sign.setText("");
        }
    }

    private void clear() {
        String text = screen.getText().toString();
        if (text.equalsIgnoreCase("0")){
            sign.setText("");
            calculator.reset();
            return;
        }
        if (text.length() == 1) {
            screen.setText("0");
            sign.setText("");
            return;
        }
        screen.setText(text.substring(0, text.length() - 1));
    }

    private void appendSymbol(String symbol){
        String text = screen.getText().toString();
        if (text.length() >= 12) return;
        if (text.equalsIgnoreCase("0") && !symbol.equalsIgnoreCase(".")) text = "";
        screen.setText(text.concat(symbol));
    }

    @Override
    public void onClick(View v) {
        if (screen.getText().toString().equals(getString(R.string.error))) {
            screen.setText(R.string.default_screen_zero);
        }
        switch (v.getId()){
            case R.id.divide:
                setOperation(Calculator.Operation.DIVIDE);
                break;
            case R.id.multiply:
                setOperation(Calculator.Operation.MULTIPLY);
                break;
            case R.id.subtract:
                setOperation(Calculator.Operation.SUBTRACT);
                break;
            case R.id.add:
                setOperation(Calculator.Operation.ADD);
                break;
            case R.id.delete:
                clear();
                break;
            case R.id.equals:
                switch (calculator.getState()){
                    case NONE:
                        calculator.reset();
                        break;
                    case ONE:
                        calculator.setSecond(Double.parseDouble(screen.getText().toString()));
                        calculate();
                }
                break;
            case R.id.dot:
                if (screen.getText().toString().contains(".")) return;
                appendSymbol(".");
                break;
            case R.id.zero:
                appendSymbol("0");
                break;
            case R.id.one:
                appendSymbol("1");
                break;
            case R.id.two:
                appendSymbol("2");
                break;
            case R.id.three:
                appendSymbol("3");
                break;
            case R.id.four:
                appendSymbol("4");
                break;
            case R.id.five:
                appendSymbol("5");
                break;
            case R.id.six:
                appendSymbol("6");
                break;
            case R.id.seven:
                appendSymbol("7");
                break;
            case R.id.eight:
                appendSymbol("8");
                break;
            case R.id.nine:
                appendSymbol("9");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(@NonNull View v) {
        if (v.getId() == R.id.delete){
            screen.setText("0");
            calculator.reset();
        }
        return false;
    }
}
