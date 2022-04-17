package com.stackcalculator.exceptions.memory;

import com.stackcalculator.exceptions.StackCalculatorException;

public class ExecutionEnvironmentException extends StackCalculatorException {

    public ExecutionEnvironmentException() {
        super();
    }

    public ExecutionEnvironmentException(String massage) {
        super(massage);
    }

    public ExecutionEnvironmentException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExecutionEnvironmentException(Throwable throwable) {
        super(throwable);
    }
}
