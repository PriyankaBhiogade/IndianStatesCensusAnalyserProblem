package com.indianstatescensus;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH = "StateCode.csv";

    public int readRecord() throws IOException, StateCensusAnalyserException {
        if(!SAMPLE_CSV_FILE_PATH.contains(".csv")){
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_EXTENSION, "Incorrect File Extension");
        }
        int counter = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean<StateCodeData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCodeData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCodeData> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodeData csvUser = csvUserIterator.next();
                counter++;
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_File, "File Not Found");
        }
        return counter;
    }
}
