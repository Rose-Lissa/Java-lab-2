package com.stackcalculator.commands.notincluded;

import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.commands.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Define implements Command {
    private final String name;
    private final String num;

    private static final Logger logger = LogManager.getLogger(Define.class);

    public Define(List<String> args) throws IllegalNumArgsException {
        if(args.size() != 2){
            throw IllegalNumArgsException.build(args.size(), 2);
        }
        name = args.get(0);
        num = args.get(1);
    }

    @Override
    public void exec(NumberStack stack, ExecutionContext context) throws UseConstantException, CommandException {
        try {
            context.addNewConstant(name, Double.parseDouble(num));
            logger.log(Level.INFO, context.getNumberCurExecutableString() + ": " +
                    "constant " + name+"="+num + " was define");
        } catch (NumberFormatException e){
            logger.log(Level.ERROR, context.getNumberCurExecutableString() + ": " +
                    "second argument isn't number");
            throw new CommandException(context.getNumberCurExecutableString() + ": " +
                    "second argument isn't number", e);
        } catch (UseConstantException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CommandException(context.getNumberCurExecutableString() + ": " +
                    e.getMessage(), e);
        }
    }
}
