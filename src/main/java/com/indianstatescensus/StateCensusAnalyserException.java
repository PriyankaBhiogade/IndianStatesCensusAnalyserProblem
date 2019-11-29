package com.indianstatescensus;

public class StateCensusAnalyserException extends Throwable {

    enum ExceptionType {
        NO_SUCH_File,ENTERED_EMPTY,ENTERED_NULL;
    }

    ExceptionType type;

    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
