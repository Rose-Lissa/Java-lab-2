package com.stackcalculator.memory;

import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.stackunits.StackUnit;
import com.stackcalculator.util.ArgumentParser;
import com.stackcalculator.util.ArgumentParserImpl;

import java.io.OutputStream;

public class ExecutionContextForStackUnits implements ExecutionContext {
    private final ArgumentParser argParser;
    private final ConstantStorage cStorage;
    private int numCurExecutableString = 0;

    public ExecutionContextForStackUnits(){
        cStorage = new ConstantStorageImpl();
        argParser = new ArgumentParserImpl(cStorage);
    }
    @Override
    public StackUnit getUnitByArg(String arg) throws UseConstantException {
        return argParser.getUnit(arg);
    }


    @Override
    public void addNewConstant(String name, double value) throws UseConstantException {
        if(!argParser.isValidConstantName(name))
            throw UseConstantException.buildWrongNameFormat();
        cStorage.addNewConstant(name, value);
    }

    @Override
    public void setNumberCurExecutableString(int num) {
        numCurExecutableString = num;
    }

    @Override
    public int getNumberCurExecutableString() {
        return numCurExecutableString;
    }

    @Override
    public OutputStream getStandardOutputStream() {
        return System.out;
    }
}
