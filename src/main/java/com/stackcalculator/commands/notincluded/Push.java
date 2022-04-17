package com.stackcalculator.commands.notincluded;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.commands.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Push implements Command {
    private final String variable;
    private static final Logger logger = LogManager.getLogger(Push.class);

    public Push(List<String> args) throws IllegalNumArgsException {
        if(args.size() != 1){
            throw IllegalNumArgsException.build(args.size(), 1);
        }

        variable = args.get(0);
    }

    @Override
    public void exec(NumberStack stack, ExecutionContext context) throws UseConstantException, CommandException {
        try {
            StackUnit num = context.getUnitByArg(variable);
            stack.push(num);
            logger.log(Level.INFO, context.getNumberCurExecutableString() + ": "
                    + num.toString() + " pushed");
        } catch (UseConstantException e){
            logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " + e.getMessage());
            throw new CommandException(context.getNumberCurExecutableString() + ": " + e.getMessage(), e);
        }
    }
}
