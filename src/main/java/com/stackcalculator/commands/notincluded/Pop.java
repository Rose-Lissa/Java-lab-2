package com.stackcalculator.commands.notincluded;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.commands.Command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Pop implements Command {
    private static final Logger logger = LogManager.getLogger(Command.class);

    public Pop(List<String> args) throws IllegalNumArgsException {
        if(args.size() != 0){
            throw IllegalNumArgsException.build(args.size(), 0);
        }
    }

    @Override
    public void exec(NumberStack stack, ExecutionContext context) throws CommandException {
        try {
            StackUnit unit = stack.pop();
            logger.log(Level.INFO, context.getNumberCurExecutableString() + ": " +
                    unit.toString() + " popped");
        } catch (UnableGettingUnitException e) {
            logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " +
                    "unable pop element, " + e.getMessage());
            throw new CommandException(context.getNumberCurExecutableString() + ": " +
                    "unable pop element: " + e.getMessage(), e);
        }
    }
}
