package com.stackcalculator.commands.basicmath;

import com.stackcalculator.commands.Command;
import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UnableGettingUnitException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.stackunits.Number;
import com.stackcalculator.memory.stackunits.StackUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Plus implements Command {
    private static final Logger logger = LogManager.getLogger(Plus.class);

    public Plus(List<String> args) throws IllegalNumArgsException {
        if(args.size() != 0){
            throw IllegalNumArgsException.build(args.size(), 0);
        }
    }

    @Override
    public void exec(NumberStack stack, ExecutionContext context) throws CommandException {
        try {
            StackUnit num1 = stack.pop();
            StackUnit num2 = stack.pop();
            StackUnit res = new Number(num1.getValue() +
                    num2.getValue());
            if(!Double.isFinite(res.getValue())){
                logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " +
                        "operation error " + num2.getValue() + "+" + num1.getValue() + "=" + res.getValue());
                throw new CommandException(context.getNumberCurExecutableString() + ": " +
                        "operation error " + num2.getValue() + "+" + num1.getValue() + "=" + res.getValue());
            }
            stack.push(res);
            logger.log(Level.INFO, context.getNumberCurExecutableString() + ": " +
                    "operation performed " + num2.getValue() + "+" + num1.getValue() + "=" + res.getValue());
        } catch (UnableGettingUnitException e) {
            logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " +
                    "unable sum " + e.getMessage());
            throw new CommandException(context.getNumberCurExecutableString() + ": " +
                    "unable sum: " + e.getMessage(), e);
        }
    }
}
