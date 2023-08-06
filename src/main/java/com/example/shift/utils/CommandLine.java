package com.example.shift.utils;

import com.lexicalscope.jewel.cli.Option;
import com.lexicalscope.jewel.cli.Unparsed;

import java.util.List;

public interface CommandLine {
    @Option(shortName = "a", description = "Ascending sort mode")
    boolean getFileSortAsc();

    @Option(shortName = "d", description = "Descending sort mode")
    boolean getFileSortDesc();

    @Option(shortName = "i", description = "Type Integer")
    boolean getFileTypeInt();

    @Option(shortName = "s", description = "Type String")
    boolean getFileTypeString();

    @Unparsed(defaultToNull = true)
    List<String> getFilesNames();
}

