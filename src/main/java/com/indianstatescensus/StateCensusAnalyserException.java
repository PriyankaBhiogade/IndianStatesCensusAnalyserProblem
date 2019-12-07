package com.indianstatescensus;

public class StateCensusAnalyserException extends Exception {

    enum ExceptionType {
        NO_SUCH_FILE,INCORRECT_INPUT_FILE,NO_SUCH_METHOD, IO_EXCEPTION,NULL_VALUE_FOUND,ILLEGAl_ACCESS;
    }
    ExceptionType type;


    public StateCensusAnalyserException(ExceptionType type, Throwable cause ) {
        this.type = type;
    }

    public StateCensusAnalyserException(ExceptionType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

}
