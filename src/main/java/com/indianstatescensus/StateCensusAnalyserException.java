package com.indianstatescensus;

public class StateCensusAnalyserException extends Throwable {

    enum ExceptionType {
        NO_SUCH_FILE,INCORRECT_TYPE
    }

    ExceptionType type;



    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
    public StateCensusAnalyserException(ExceptionType type, String message, RuntimeException e) {
        super(message);
        this.type = type;
    }
}
