package com.stackcalculator;

import com.stackcalculator.commands.basicmath.Plus;
import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.Number;
import com.stackcalculator.memory.stackunits.StackUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlusTest {
    public ExecutionContext context;
    public NumberStack stack;

    @BeforeEach
    public void init(){
        context = new ExecutionContextForStackUnits();
        stack = new NumberUnitStack();
    }

    @Test
    public void constructorExceptions(){
        String[] highArgs = {"a"};

        assertThrows(IllegalNumArgsException.class,
                () -> new Plus(Arrays.stream(highArgs).toList()));
    }

    @Test
    public void simpleSum() throws CommandException, UnableGettingUnitException {
        Plus plus = new Plus(List.of());
        stack.push(new Number(10.1));
        stack.push(new Number(23.7));
        stack.push(new Number(56.0));
        plus.exec(stack, context);
        plus.exec(stack, context);
        StackUnit unit = stack.pop();
        assertEquals(unit.getValue(),89.8);

    }
}
