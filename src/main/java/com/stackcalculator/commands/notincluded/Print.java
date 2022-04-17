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

import java.io.PrintWriter;
import java.util.List;

public class Print implements Command {
    private static final Logger logger = LogManager.getLogger(Print.class);
    public Print(List<String> args) throws IllegalNumArgsException {
        if(args.size() != 0){
            throw IllegalNumArgsException.build(args.size(), 0);
        }
    }

    @Override
    public void exec(NumberStack stack, ExecutionContext context) throws CommandException {
        try {
            StackUnit num = stack.peek();
            PrintWriter writer = new PrintWriter(
                    context.getStandardOutputStream(),
                    true);
            writer.println(num.getValue());

            logger.log(Level.INFO, context.getNumberCurExecutableString() + ": " +
                    num + " printed");
        } catch (UnableGettingUnitException e) {
            logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " +
                    "unable print value " + e.getMessage());
            throw new CommandException(context.getNumberCurExecutableString() + ": " +
                    "unable print value: " + e.getMessage(), e);
        }
    }
}
