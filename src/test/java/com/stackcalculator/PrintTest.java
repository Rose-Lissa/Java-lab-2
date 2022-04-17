package com.stackcalculator;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.Number;
import com.stackcalculator.commands.notincluded.Print;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintTest {
    public ExecutionContext context;
    public NumberStack stack;
    public ByteArrayOutputStream stream;

    @BeforeEach
    public void init(){
        stream = new ByteArrayOutputStream();
        context = new ExecutionContextForStackUnits(){
            @Override
            public OutputStream getStandardOutputStream() {
                return stream;
            }
        };
        stack = new NumberUnitStack();
    }

    @Test
    public void constructorExceptions(){
        String[] lowArgs = {"a"};

        assertThrows(IllegalNumArgsException.class,
                () -> new Print(Arrays.stream(lowArgs).toList()));
    }

    @Test
    public void printWithFilledStack() throws CommandException {
        Print command = new Print(new ArrayList<>());

        stack.push(new Number(123.4));
        stack.push(new Number(126.7));

        command.exec(stack, context);

        String out = stream.toString();

        assertEquals("126.7\r\n", out);
    }

    @Test
    public void printWithEmptyStack() throws CommandException {
        Print command = new Print(new ArrayList<>());

        assertThrows(CommandException.class,
                () -> command.exec(stack, context));
    }
}
