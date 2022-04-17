package com.stackcalculator.exceptions.commands.factory;

public class BuildFactoryException extends CommandFactoryException{
    public BuildFactoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
