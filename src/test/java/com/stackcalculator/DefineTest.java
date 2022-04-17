package com.stackcalculator;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.commands.notincluded.Define;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefineTest {
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
        String[] maxArgs = {"a", "b", "10"};

        assertThrows(IllegalNumArgsException.class,
                () -> new Define(Arrays.stream(lowArgs).toList()));
        assertThrows(IllegalNumArgsException.class,
                () -> new Define(Arrays.stream(maxArgs).toList()));
    }

    @Test
    public void simpleDef() throws CommandException, UseConstantException {
        String[] args1 = {"constant", "123.65"};

        Define command = new Define(Arrays.stream(args1).toList());
        command.exec(stack, context);

        StackUnit number = context.getUnitByArg(args1[0]);
        assertEquals(number.getValue(), Double.valueOf(args1[1]));
    }

    @Test
    public void twinsDef() throws CommandException, UseConstantException {
        String[] args1 = {"constant", "123.65"};
        String[] args2 = {"constant", "123.65"};

        Define command1 = new Define(Arrays.stream(args1).toList());
        Define command2 = new Define(Arrays.stream(args2).toList());

        command1.exec(stack, context);
        assertThrows(UseConstantException.class,
                () -> command2.exec(stack, context));
    }

    @Test
    public void wrongArgumentsFormat() throws IllegalNumArgsException {
        String[] args1 = {"123.65", "123.65"};
        String[] args2 = {"constant", "abc"};

        Define command1 = new Define(Arrays.stream(args1).toList());
        Define command2 = new Define(Arrays.stream(args2).toList());

        assertThrows(UseConstantException.class,
                () -> command1.exec(stack, context));
        assertThrows(CommandException.class,
                () -> command2.exec(stack, context));
    }
}
