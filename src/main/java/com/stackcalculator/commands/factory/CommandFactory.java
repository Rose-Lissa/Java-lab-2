package com.stackcalculator.commands.factory;

import com.stackcalculator.commands.Command;
import com.stackcalculator.exceptions.commands.IllegalNumArgsException;
import com.stackcalculator.exceptions.commands.factory.CommandFactoryException;

import java.util.List;

public interface CommandFactory {
    Command makeCommand(String commandName, List<String> args) throws CommandFactoryException, IllegalNumArgsException;
    void include(String fileName) throws CommandFactoryException;
}
