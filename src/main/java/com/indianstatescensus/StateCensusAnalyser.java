package com.indianstatescensus;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
    private static String SAMPLE_CSV_FILE_PATH;
    private static String GSON_FILE;

    public StateCensusAnalyser(String fileName) {
        this.SAMPLE_CSV_FILE_PATH = fileName;
    }

    public StateCensusAnalyser(String stateCensusFilePath, String jsonPath) {
        this.SAMPLE_CSV_FILE_PATH = stateCensusFilePath;
        this.GSON_FILE=jsonPath;
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
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found", ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e);
        }
        return counter;
    }

    public static int readCensusRecord() throws IOException, StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<StateCensusData> stateCensusData = csvToBean.parse();
            sortStatePopulation(stateCensusData);
            sortStateName(stateCensusData);
            sortStateDensity(stateCensusData);
            return stateCensusData.size();
        } catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found", ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e);
        }
    }

    public static void sortStateName(List<StateCensusData> csvCensusList) throws IOException {
        Comparator<StateCensusData> c = (s1, s2) -> s1.getStateName().compareTo(s2.getStateName());
        csvCensusList.sort(c);
        convertCSVtoJSON(csvCensusList );
    }

    private static void sortStatePopulation(List<StateCensusData> csvCensusList ) throws IOException {
        Comparator<StateCensusData> c = (s1, s2) ->
                Integer.parseInt(s2.getPopulation()) - Integer.parseInt(s1.getPopulation());
                csvCensusList.sort(c);
          convertCSVtoJSON(csvCensusList);

    }

    private static void sortStateDensity(List<StateCensusData> csvCensusList ) throws IOException {
        Comparator<StateCensusData> c = (s1, s2) ->
                Integer.parseInt(s2.getDensityPerSqKm()) - Integer.parseInt(s1.getDensityPerSqKm());
        csvCensusList.sort(c);
        convertCSVtoJSON(csvCensusList);

    }

    public static void convertCSVtoJSON( List<StateCensusData> stateCensusData) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(stateCensusData);
        FileWriter fileWriter = new FileWriter(GSON_FILE);
        fileWriter.write(json);
        fileWriter.close();
    }
}