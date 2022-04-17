package com.stackcalculator.memory.stackunits;

import com.stackcalculator.exceptions.memory.UseConstantException;

public interface StackUnit {
    double getValue();
    void setValue(double value) throws UseConstantException;
}
