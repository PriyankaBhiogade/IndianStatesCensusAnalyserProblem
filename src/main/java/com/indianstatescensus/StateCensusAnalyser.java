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
    private static String SAMPLE_CSV_FILE_PATH;

    public StateCensusAnalyser(String fileName) {
        this.SAMPLE_CSV_FILE_PATH = fileName;
    }

    public static int readRecord() throws IOException, StateCensusAnalyserException {
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
        } catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found",ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e);
        }
        return counter;
    }

    public static int readCensusRecord() throws IOException, StateCensusAnalyserException {
        int counter = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCensusData> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusData csvUser = csvUserIterator.next();
                counter++;
            }
        }catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found",ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER" ,e);
        }
        return counter;
    }
}