package com.stackcalculator;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.Number;
import com.stackcalculator.commands.notincluded.Pop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopTest {
    public ExecutionContext context;
    public NumberStack stack;

    @BeforeEach
    public void init(){
        context = new ExecutionContextForStackUnits();
        stack = new NumberUnitStack();
    }

    @Test
    public void constructorExceptions(){
        String[] lowArgs = {"a"};

        assertThrows(IllegalNumArgsException.class,
                () -> new Pop(Arrays.stream(lowArgs).toList()));
    }

    @Test
    public void popFromEmptyStack() throws IllegalNumArgsException {
        Pop pop = new Pop(new ArrayList<>());

        assertThrows(CommandException.class,
                () -> pop.exec(stack, context));
    }

    @Test
    public void popFromFilledStack() throws CommandException {
        Pop pop = new Pop(new ArrayList<>());

        stack.push(new Number(123.4));
        stack.push(new Number(126.7));

        pop.exec(stack, context);

        assertEquals(stack.size(), 1);
    }
}
