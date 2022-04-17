package com.stackcalculator.memory;

import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.memory.stackunits.StackUnit;

import java.util.EmptyStackException;
import java.util.Stack;

public class NumberUnitStack implements NumberStack {
    private final Stack<StackUnit> stack;

    public NumberUnitStack() {
        stack = new Stack<>();
    }

    @Override
    public StackUnit pop() throws UnableGettingUnitException {
        try {
            return stack.pop();
        } catch (EmptyStackException ex) {
            throw UnableGettingUnitException.buildEmptyStack();
        }
    }

    @Override
    public void push(StackUnit unit) {
        stack.push(unit);
    }

    @Override
    public StackUnit peek() throws UnableGettingUnitException {
        try{
            return stack.peek();
        }catch (EmptyStackException ex) {
            throw UnableGettingUnitException.buildEmptyStack();
        }
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
