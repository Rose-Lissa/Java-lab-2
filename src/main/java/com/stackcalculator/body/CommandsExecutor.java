package com.stackcalculator.body;

import com.stackcalculator.commands.Command;
import com.stackcalculator.exceptions.ExecutionException;

import java.util.Map;

public interface CommandsExecutor {
    void exec(Map<Integer, Command> storage) throws ExecutionException;
}
