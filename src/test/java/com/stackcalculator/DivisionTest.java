package com.stackcalculator;

import com.stackcalculator.commands.basicmath.Division;
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

public class DivisionTest {
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
                () -> new Division(Arrays.stream(highArgs).toList()));
    }

    @Test
    public void simpleDiv() throws CommandException, UnableGettingUnitException {
        Division div = new Division(List.of());
        stack.push(new Number(10.2));
        stack.push(new Number(2.0));
        div.exec(stack, context);
        StackUnit unit = stack.pop();
        assertEquals(unit.getValue(),5.1);
    }

    @Test
    public void divByZero() throws IllegalNumArgsException {
        Division div = new Division(List.of());
        stack.push(new Number(10.2));
        stack.push(new Number(0));
        assertThrows(CommandException.class, () -> div.exec(stack, context));
    }
}
