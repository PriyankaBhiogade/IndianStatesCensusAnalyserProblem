package com.indianstatescensus;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.w3c.dom.css.Counter;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/Desktop/IndianStatesCensusAnalyserProblem/StateCode.csv";

    public int readRecord() {
        int counter = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
