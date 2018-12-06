package com.ljubenvassilev.calculator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public final class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private TextView screen;
    private TextView sign;
    private Calculator calculator;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        sign = findViewById(R.id.sign);
        calculator = Calculator.getInstance();
        findViewById(R.id.delete).setOnLongClickListener(this);
        resources = getResources();
    }

    private void setOperation(Calculator.Operation operation){
        switch (calculator.getState()){
            case NONE:
                calculator.setOperation(operation);
                calculator.setFirst(Double.parseDouble(screen.getText().toString()));
                screen.setText(R.string.zero);
                calculator.setState(Calculator.State.ONE);
                break;
            case ONE:
                calculator.setSecond(Double.parseDouble(screen.getText().toString()));
                calculate();
                calculator.setOperation(operation);
        }
        switch (operation){
            case ADD:
                sign.setText(resources.getText(R.string.add));
                break;
            case SUBTRACT:
                sign.setText(resources.getText(R.string.subtract));
                break;
            case MULTIPLY:
                sign.setText(resources.getText(R.string.multiply));
                break;
            case DIVIDE:
                sign.setText(resources.getText(R.string.divide));
                break;
            default:
                sign.setText(resources.getText(R.string.empty));
        }
    }

    private void calculate() {
        try {
            String result = calculator.calculate();
            screen.setText(result);
            sign.setText(resources.getText(R.string.empty));
            calculator.setFirst(Double.parseDouble(result));
        } catch (IncorrectInputException e) {
            screen.setText(R.string.error);
            calculator.reset();
        } finally {
            sign.setText(resources.getText(R.string.empty));
        }
    }

    private void clear() {
        String text = screen.getText().toString();
        if (text.equalsIgnoreCase(resources.getText(R.string.zero).toString())){
            sign.setText(resources.getText(R.string.empty));
            calculator.reset();
            return;
        }
        if (text.length() == 1) {
            screen.setText(resources.getText(R.string.empty));
            sign.setText(resources.getText(R.string.empty));
            return;
        }
        screen.setText(text.substring(0, text.length() - 1));
    }

    private void appendSymbol(String symbol){
        String text = screen.getText().toString();
        if (text.length() >= 12) return;
        if (text.equalsIgnoreCase(resources.getText(R.string.zero).toString()) &&
                !symbol.equalsIgnoreCase(resources.getText(R.string.dot).toString())) {
            text = resources.getText(R.string.empty).toString();
        }
        screen.setText(text.concat(symbol));
    }

    @Override
    public void onClick(View v) {
        if (screen.getText().toString().equals(getString(R.string.error))) {
            screen.setText(R.string.zero);
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
                if (screen.getText().toString().contains(resources.getText(R.string.dot))) return;
                appendSymbol(resources.getText(R.string.dot).toString());
                break;
            case R.id.zero:
                appendSymbol(resources.getText(R.string.zero).toString());
                break;
            case R.id.one:
                appendSymbol(resources.getText(R.string.one).toString());
                break;
            case R.id.two:
                appendSymbol(resources.getText(R.string.two).toString());
                break;
            case R.id.three:
                appendSymbol(resources.getText(R.string.three).toString());
                break;
            case R.id.four:
                appendSymbol(resources.getText(R.string.four).toString());
                break;
            case R.id.five:
                appendSymbol(resources.getText(R.string.five).toString());
                break;
            case R.id.six:
                appendSymbol(resources.getText(R.string.six).toString());
                break;
            case R.id.seven:
                appendSymbol(resources.getText(R.string.seven).toString());
                break;
            case R.id.eight:
                appendSymbol(resources.getText(R.string.eight).toString());
                break;
            case R.id.nine:
                appendSymbol(resources.getText(R.string.nine).toString());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(@NonNull View v) {
        if (v.getId() == R.id.delete){
            screen.setText(resources.getText(R.string.zero).toString());
            calculator.reset();
        }
        return false;
    }
}
