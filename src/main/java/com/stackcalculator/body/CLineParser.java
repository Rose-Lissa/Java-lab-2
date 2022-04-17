package com.stackcalculator.body;

import com.stackcalculator.exceptions.body.CLineParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CLineParser {
    private String fileName = "";
    private static final Logger logger = LogManager.getLogger(CLineParser.class);
    ReadMode readMode;

    public CLineParser(String[] args) throws CLineParseException {
        parse(args);
    }

    private void parse(String[] args) throws CLineParseException {
        logger.log(Level.INFO, "parse cline arguments...");
        if (args.length == 1) {
            fileName = args[0];
            readMode = ReadMode.FROM_FILE;
        } else if (args.length == 0) {
            readMode = ReadMode.FROM_SYSTEM_INPUT;
        } else {
            logger.log(Level.ERROR, "invalid format of command line arguments");
            throw new CLineParseException("a lot of arguments");
        }
        logger.log(Level.INFO, "parse cline arguments successful");
    }

    public String getFileName(){
        return fileName;
    }

    public ReadMode getReadMode(){
        return readMode;
    }
}
