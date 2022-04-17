package com.stackcalculator;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.commands.notincluded.Push;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PushTest {
    public ExecutionContext context;
    public NumberStack stack;

    @BeforeEach
    public void init(){
        context = new ExecutionContextForStackUnits();
        stack = new NumberUnitStack();
    }

    @Test
    public void constructorExceptions(){
        String[] highArgs = {"a", "b"};

        assertThrows(IllegalNumArgsException.class,
                () -> new Push(Arrays.stream(highArgs).toList()));
        assertThrows(IllegalNumArgsException.class,
                () -> new Push(new ArrayList<>()));
    }

    @Test
    public void pushNumber() throws CommandException, UseConstantException, UnableGettingUnitException {
        String[] intNumberArgs = {"10"};
        String[] doubleNumberArgs = {"11.23"};
        String[] badFormatNumberArgs = {"23.121.12"};

        Push commandIntNumber = new Push(Arrays.stream(intNumberArgs).toList());
        Push commandDoubleNumber = new Push(Arrays.stream(doubleNumberArgs).toList());
        Push commandBadFormatNumber = new Push(Arrays.stream(badFormatNumberArgs).toList());

        commandIntNumber.exec(stack, context);
        commandDoubleNumber.exec(stack, context);
        assertEquals(2, stack.size());

        StackUnit doubleNumber = stack.pop();
        StackUnit intNumber = stack.pop();
        assertEquals(doubleNumber.getValue(), Double.parseDouble(doubleNumberArgs[0]));
        assertEquals(intNumber.getValue(), Double.parseDouble(intNumberArgs[0]));

        assertThrows(UseConstantException.class,
                () -> commandBadFormatNumber.exec(stack, context));
    }

    @Test
    public void pushConstant() throws UseConstantException, CommandException, UnableGettingUnitException {
        context.addNewConstant("hello", 12.11);
        String[] existingConstantArgs = {"hello"};
        String[] non_existingConstantArgs = {"world"};

        Push commandExisting = new Push(Arrays.stream(existingConstantArgs).toList());
        Push commandNon_existing = new Push(Arrays.stream(non_existingConstantArgs).toList());

        commandExisting.exec(stack, context);
        assertEquals(stack.size(), 1);

        StackUnit existingConstant = stack.pop();
        assertEquals(existingConstant.getValue(), 12.11);

        assertThrows(UseConstantException.class,
                ()-> commandNon_existing.exec(stack, context));
    }

}
