package com.example.shift.service;

import java.io.BufferedReader;

public class BufferedReaderValue {

    private BufferedReader inBufferedReader;
    private String value;

    public BufferedReaderValue(BufferedReader inBufferedReader) {
        this.inBufferedReader = inBufferedReader;
        this.value = null;
    }

    public BufferedReader getInBufferedReader() {
        return inBufferedReader;
    }

    public void setInBufferedReader(BufferedReader inBufferedReader) {
        this.inBufferedReader = inBufferedReader;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
