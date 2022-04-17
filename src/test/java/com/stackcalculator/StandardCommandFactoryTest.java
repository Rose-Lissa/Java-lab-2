package com.stackcalculator;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.factory.CommandFactoryException;
import com.stackcalculator.exceptions.memory.ExecutionEnvironmentException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.commands.Command;
import com.stackcalculator.commands.factory.CommandFactory;
import com.stackcalculator.commands.factory.StandardCommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardCommandFactoryTest {
    public CommandFactory factory;
    public Command command;
    public ExecutionContext context;
    public NumberStack stack;

    @BeforeEach
    public void init() throws CommandFactoryException {
        context = new ExecutionContextForStackUnits();
        stack = new NumberUnitStack();
        factory = StandardCommandFactory.builder().build();
    }

    @Test
    public void buildAndPushTest() throws ExecutionEnvironmentException, CommandException, CommandFactoryException {
        command = factory.makeCommand("PUSH", List.of("10.1"));
        command.exec(stack, context);
        StackUnit unit = stack.peek();
        assertEquals(unit.getValue(), 10.1);
    }
}
