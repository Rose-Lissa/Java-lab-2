package com.stackcalculator.exceptions.body;

import com.stackcalculator.exceptions.StackCalculatorException;

public class CLineParseException extends StackCalculatorException {
    public CLineParseException() {
        super();
    }

    public CLineParseException(String massage) {
        super(massage);
    }

    public CLineParseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CLineParseException(Throwable throwable) {
        super(throwable);
    }
}
