package com.stackcalculator.memory;

import com.stackcalculator.exceptions.memory.UseConstantException;
import com.stackcalculator.memory.stackunits.StackUnit;

public interface ConstantStorage {
    StackUnit getConstantByName(String name) throws UseConstantException;
    void addNewConstant(String name, double value) throws UseConstantException;
}
