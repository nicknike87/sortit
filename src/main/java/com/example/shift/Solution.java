package com.example.shift;

import com.example.shift.utils.CommandLineParser;
import com.example.shift.service.Processor;

public class Solution {
    public static void main(String[] args) {
        try {
            Processor processor = new Processor.Builder()
                    .commandLineParser(new CommandLineParser(args))
                    .fileMap()
                    .build();
            processor.start();

            System.out.println("Processing completed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
