package com.example.shift.service;

import com.example.shift.utils.CommandLine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileMap {

    private BufferedWriter outBufferedWriter;
    private List<BufferedReaderValue> inBufferedReaderList = new ArrayList<>();

    public FileMap(CommandLine commandLine) throws Exception {

        outBufferedWriter = openOutBufferedWriter(commandLine.getFilesNames().get(0));
        openInBufferedReader(commandLine.getFilesNames()
                .stream()
                .skip(1)
                .collect(Collectors.toList()));
    }

    public void sort() throws Exception {
        BufferedReaderValue br;

        while (!inBufferedReaderList.isEmpty()) {
            if (Processor.fileSortAsc) {
                br = inBufferedReaderList.get(0);
            } else {
                br = inBufferedReaderList.get(inBufferedReaderList.size() - 1);
            }
            writeToBuffer(br.getValue());
            inBufferedReaderList.remove(br);
            if (!insertNewValueInList(br)) closeInBufferedReader(br.getInBufferedReader());
        }

        closeOutBufferedWriter(outBufferedWriter);
    }

    private boolean insertNewValueInList(BufferedReaderValue br) {
        try {
            String line;

            while ((line = br.getInBufferedReader().readLine()) != null) {
                br.setValue(line);
                try {
                    inBufferedReaderList.add(findIndexToInsert(line), br);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + " " + "Received not an integer, but an integer was expected, skip it");
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public int findIndexToInsert(String line) {
        int loIndex = 0;
        int hiIndex = inBufferedReaderList.size();
        int midIndex = 0;
        int resultCompare = 0;

        while (loIndex < hiIndex) {
            midIndex = loIndex + (hiIndex - loIndex) / 2;
            if (Processor.fileTypeInt) {
                resultCompare = Integer.parseInt(line) - Integer.parseInt(inBufferedReaderList.get(midIndex).getValue());
            } else {
                resultCompare = line.compareTo(inBufferedReaderList.get(midIndex).getValue());
            }
            if (resultCompare <= 0) {
                hiIndex = midIndex;
            } else {
                loIndex = midIndex + 1;
            }
        }
        return loIndex;
    }

    private void writeToBuffer(String value) {
        try {
            outBufferedWriter.write(value);
            outBufferedWriter.newLine();

        } catch (IOException e) {
            System.out.println("Unable to write into outputfile value " + value);
        }
    }

    private BufferedWriter openOutBufferedWriter(String fileName) throws Exception {

        deleteFileIfExists(fileName);

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(fileName));

        } catch (IOException e) {
            throw new Exception("Unable to open outputfile" + fileName);
        }
        return bw;
    }

    private void openInBufferedReader(List<String> fileName) {

        for (String fn : fileName) {
            try {
                String line;
                BufferedReaderValue br = new BufferedReaderValue(new BufferedReader(new FileReader(fn)));
                if (!insertNewValueInList(br)) closeInBufferedReader(br.getInBufferedReader());
            } catch (IOException e) {
                System.out.println("Unable to open inputfile " + fileName + ", skip it");
            }
        }
    }

    private void closeOutBufferedWriter(BufferedWriter bw) {
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to close outputfile, skip it");
        }
    }

    private void closeInBufferedReader(BufferedReader br) {
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Unable to open inputfile, skip it");
        }
    }

    private void deleteFileIfExists(String filename) {
        File file = new File(filename);
        if (file.isFile()) file.delete();
    }
}
