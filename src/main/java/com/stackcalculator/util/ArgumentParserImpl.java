package com.stackcalculator.util;

import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.ConstantStorage;
import com.stackcalculator.memory.stackunits.Number;
import com.stackcalculator.memory.stackunits.StackUnit;

import java.util.regex.Pattern;

public class ArgumentParserImpl implements ArgumentParser{
    private static final Pattern numberPattern = Pattern.compile("^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$");
    private static final Pattern constantNamePattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");
    private final ConstantStorage storage;

    public ArgumentParserImpl(ConstantStorage storage){
        this.storage = storage;
    }

    @Override
    public StackUnit getUnit(String arg) throws UseConstantException {
        StackUnit unit;
        if(matches(arg, numberPattern)) {
            double number = Double.parseDouble(arg);
            unit = new Number(number);
        }
        else if(isValidConstantName(arg)){
            unit = storage.getConstantByName(arg);
        }
        else {
            throw UseConstantException.buildWrongNameFormat();
        }

        return unit;
    }

    @Override
    public boolean isValidConstantName(String name) {
        return matches(name, constantNamePattern);
    }

    private boolean matches(String arg, Pattern pattern){
        return pattern.matcher(arg).matches();
    }
}
