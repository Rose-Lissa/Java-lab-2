package com.stackcalculator.body;

import com.stackcalculator.commands.Command;
import com.stackcalculator.exceptions.CommandParseException;

import java.util.NavigableMap;

public interface CommandsParser {
    NavigableMap<Integer, Command> parse() throws CommandParseException;
}
