package com.stackcalculator.exceptions.commands.factory;

public class InvalidCommandNameException extends CommandFactoryException{
    InvalidCommandNameException(){
        super();
    }

    InvalidCommandNameException(String message){
        super(message);
    }

    public static InvalidCommandNameException buildUnknownCommand(String name){
        return new InvalidCommandNameException("command " + name + " no exists");
    }
}
