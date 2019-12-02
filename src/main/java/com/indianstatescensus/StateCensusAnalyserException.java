package com.indianstatescensus;

import java.nio.file.NoSuchFileException;

public class StateCensusAnalyserException extends Throwable {
    enum ExceptionType {
        NO_SUCH_FILE,INCORRECT_INPUT_FILE
    }

    ExceptionType type;

    public StateCensusAnalyserException(ExceptionType type, String message, NoSuchFileException ex) {
        super(message);
        this.type = type;
    }
    public StateCensusAnalyserException(ExceptionType type, String message, RuntimeException e) {
        super(message);
        this.type = type;
    }
}
