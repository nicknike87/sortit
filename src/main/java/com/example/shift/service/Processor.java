package com.example.shift.service;

import com.example.shift.utils.CommandLineParser;

public class Processor {
    public static boolean fileSortAsc;
    public static boolean fileTypeInt;
    private FileMap fileMap;

    public void start() throws Exception{
        fileMap.sort();
    }

    private Processor(Builder builder) {
        if (builder != null) {
            fileMap = builder.fileMap;
        }
    }

    public static class Builder {
        private CommandLineParser commandLineParser;
        private FileMap fileMap;

        public Builder commandLineParser(CommandLineParser commandLineParser) {
            this.commandLineParser = commandLineParser;
            return this;
        }

        public Builder fileMap() throws Exception{
            this.fileMap = new FileMap(this.commandLineParser.getCommandLine());
            return this;
        }

        public Processor build() {
            return new Processor(this);
        }
    }
}
