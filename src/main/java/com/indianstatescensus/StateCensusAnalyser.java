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

    public int readStateCodeData(String fileName) throws IOException, StateCensusAnalyserException {
        StateCodeData stateCodeData = new StateCodeData();
        int stateCode = readRecord(stateCodeData,fileName);
        return stateCode;
    }

    public int readCensusRecord(String fileName) throws IOException, StateCensusAnalyserException {
        StateCensusData stateCensusData = new StateCensusData();
        int stateCensus = readRecord(stateCensusData,fileName);
        return stateCensus;
    }

    public static int readRecord(Object stateCodeData,String fileName) throws IOException, StateCensusAnalyserException {
        Object obj = stateCodeData;
        int counter = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(fileName));
        ) {
            CsvToBean<Object> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Object.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<Object> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                Object csvUser = csvUserIterator.next();
                counter++;
            }
        } catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found", ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e);
        }
        return counter;
    }
}
