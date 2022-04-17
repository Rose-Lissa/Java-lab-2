package com.stackcalculator.memory;

import com.stackcalculator.exceptions.memory.NumberStackException;
import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.memory.stackunits.StackUnit;

public interface NumberStack {
    StackUnit pop() throws UnableGettingUnitException;
    void push(StackUnit unit);
    StackUnit peek() throws UnableGettingUnitException;
    boolean isEmpty();
    int size();
}
