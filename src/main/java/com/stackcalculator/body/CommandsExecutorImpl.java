package com.stackcalculator.body;

import com.stackcalculator.commands.Command;
import com.stackcalculator.exceptions.ExecutionException;
import com.stackcalculator.exceptions.commands.CommandException;
import com.stackcalculator.exceptions.memory.ExecutionEnvironmentException;
import com.stackcalculator.memory.ExecutionContext;
import com.stackcalculator.memory.ExecutionContextForStackUnits;
import com.stackcalculator.memory.NumberStack;
import com.stackcalculator.memory.NumberUnitStack;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CommandsExecutorImpl implements CommandsExecutor{
    private static final Logger logger = LogManager.getLogger(CommandsExecutorImpl.class);
    @Override
    public void exec(Map<Integer, Command> storage) throws ExecutionException {
        logger.log(Level.INFO, "execution is in progress...");
        NumberStack stack = new NumberUnitStack();
        ExecutionContext context = new ExecutionContextForStackUnits();

        for(Map.Entry<Integer, Command> entry: storage.entrySet()){
            context.setNumberCurExecutableString(entry.getKey());
            try {
                entry.getValue().exec(stack, context);
            } catch (ExecutionEnvironmentException | CommandException e) {
                throw new ExecutionException(e.getMessage(), e);
            }
        }
        logger.log(Level.INFO, "successful execution");
    }
}
