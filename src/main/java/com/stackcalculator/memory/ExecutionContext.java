package com.stackcalculator.memory;

import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.stackunits.StackUnit;

import java.io.OutputStream;

public interface ExecutionContext {
    StackUnit getUnitByArg(String arg) throws UseConstantException;
    void addNewConstant(String name, double value) throws UseConstantException;
    void setNumberCurExecutableString(int num);
    int getNumberCurExecutableString();
    OutputStream getStandardOutputStream();
}
