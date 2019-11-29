package com.indianstatescensus;

public class StateCensusAnalyserException extends Throwable {

    enum ExceptionType {
        NO_SUCH_File,INVALID_EXTENSION
    }

    ExceptionType type;

    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
