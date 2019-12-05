package com.indianstatescensus;

import java.nio.file.NoSuchFileException;

public class StateCensusAnalyserException extends Throwable {
    enum ExceptionType {
        NO_SUCH_FILE,INCORRECT_INPUT_FILE,NO_SUCH_METHOD
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
    public StateCensusAnalyserException(ExceptionType type, String message, NoSuchMethodException e) {
        super(message);
        this.type = type;
    }
}
