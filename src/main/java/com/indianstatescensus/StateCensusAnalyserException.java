package com.indianstatescensus;

import java.nio.file.NoSuchFileException;

public class StateCensusAnalyserException extends Throwable {

    enum ExceptionType {
        NO_SUCH_FILE,INVALID_EXTENSION
    }

    ExceptionType type;

    public StateCensusAnalyserException(ExceptionType type, String message, NoSuchFileException e) {
        super(message);
        this.type = type;
    }
}
