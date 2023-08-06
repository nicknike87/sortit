package com.example.shift.utils;

import com.example.shift.service.Processor;
import com.lexicalscope.jewel.cli.ArgumentValidationException;
import com.lexicalscope.jewel.cli.CliFactory;

import java.util.Arrays;

public class CommandLineParser {

    private final CommandLine commandLine;

    public CommandLineParser(final String[] commandLineArguments) throws Exception {

        commandLine = parseCommandLine(commandLineArguments);
        if (commandLine == null) {
            throw new Exception("Failed to process input parameters.");
        } else {
            validateCommandLine(commandLine);
        }
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }

    private CommandLine parseCommandLine(final String[] commandLineArguments) throws Exception {

        CommandLine commandLine = null;
        try {
            commandLine = CliFactory.parseArguments(CommandLine.class, commandLineArguments);
        } catch (ArgumentValidationException e) {
            throw new Exception("ERROR: Unable to parse command-line arguments "
                    + Arrays.toString(commandLineArguments) + " due to: "
                    + e.getValidationFailures() + " , Fix it please...");
        }
        return commandLine;
    }

    private void validateCommandLine(CommandLine commandLine) throws Exception {

        if (commandLine.getFileSortAsc() && commandLine.getFileSortDesc()) {
            throw new Exception("You have entered both mutually exclusive parameters -a -d, Fix it please...");
        }

        if (commandLine.getFileTypeInt() && commandLine.getFileTypeString()) {
            throw new Exception("You have entered both mutually exclusive parameters -i -s, Fix it please...");
        } else if (!commandLine.getFileTypeInt() && !commandLine.getFileTypeString()) {
            throw new Exception("You didn't enter a required parameter -i or -s, Fix it please...");
        }

        if (commandLine.getFilesNames().size() < 2) {
            throw new Exception("At least two filenames must be entered, Fix it please...");
        }

        Processor.fileSortAsc = commandLine.getFileSortAsc() || !commandLine.getFileSortDesc();
        Processor.fileTypeInt = commandLine.getFileTypeInt();
    }
}
