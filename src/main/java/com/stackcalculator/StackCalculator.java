package com.stackcalculator;

import com.stackcalculator.body.CLineParser;
import com.stackcalculator.body.CommandsExecutor;
import com.stackcalculator.body.CommandsExecutorImpl;
import com.stackcalculator.body.CommandsParser;
import com.stackcalculator.body.CommandsParserImpl;
import com.stackcalculator.exceptions.CommandParseException;
import com.stackcalculator.exceptions.ExecutionException;
import com.stackcalculator.exceptions.body.CLineParseException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StackCalculator {
    public static Logger logger = LogManager.getLogger(StackCalculator.class);

    public static void main(String[] args) {
        try {
            logger.log(Level.INFO, "start");
            CLineParser cLParser = new CLineParser(args);
            CommandsParser commandsParser = CommandsParserImpl.buildWithCLineArguments(cLParser);
            var commands  = commandsParser.parse();
            CommandsExecutor executor = new CommandsExecutorImpl();
            executor.exec(commands);
            logger.log(Level.INFO, "successful completion");
        } catch (CLineParseException e) {
            System.err.println("Error parse command line args: \n\t" + e.getMessage());
            logger.log(Level.ERROR, "unsuccessful completion");
        } catch (CommandParseException e) {
            System.err.println("Error parse commands: \n\t" + e.getMessage());
            logger.log(Level.ERROR, "unsuccessful completion");
        } catch (ExecutionException e) {
            System.err.println("Error execution commands: \n\t" + e.getMessage());
            logger.log(Level.ERROR, "unsuccessful completion");
        }
    }
}
