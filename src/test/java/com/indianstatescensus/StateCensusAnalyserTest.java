package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StateCensusAnalyserTest {
    String stateCensusFilePath = "StateCensusData.csv";
    String JSONPath = "StateCensus.json";
    private static final String STATE_CENSUS_DATA = "com.indianstatescensus.StateCensusData";

    //UC1
    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnCountAndSortByStateNameWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            List<StateCensusData> recordCount = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals(29, recordCount.size());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenFileImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCensusData123.csv", JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenExtensionIncorrect_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCode.csv", JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenDelimiterIncorrect_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenHeaderIncorrect_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

//    UC2
    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnTopNameSortByStateNameWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getStateName");
        try {
            List<StateCensusData> topStateName = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("Andhra Pradesh", topStateName.get(0).getStateName());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getState");
        try {
            List<StateCensusData> topStateName = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("Andhra Pradesh", topStateName.get(0).getStateName());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }

//   UC3
    @Test
    public void givenStateCensusCSTFileGetPopulationMethod_whenProper_ReturnHighestPopulationAndSortByPopulationWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getPopulation");
        try {
            List<StateCensusData> highestPopulation = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("10116752", highestPopulation.get(0).getPopulation());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFileWithGetPopulationMethod_whenImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getPopulation123");
        try {
            List<StateCensusData> highestPopulation = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("10116752", highestPopulation.get(0).getPopulation());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }

//    UC4
    @Test
    public void givenStateCensusCSTFileGetDensityMethod_whenProper_ReturnHighestDensityAndSortByDensityWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getDensityPerSqKm");
    try {
        List<StateCensusData> highestDensity = stateCensusAnalyser.readCensusRecord();
        Assert.assertEquals("1382611", highestDensity.get(0).getDensityPerSqKm());
    } catch (StateCensusAnalyserException | NoSuchMethodException e) {
        System.out.println(e.getMessage());
        Assert.assertEquals("File Not Found", e.getMessage());
    }
}

    @Test
    public void givenStateCensusCSVFileWithGetDensityMethod__whenImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getDensity");
        try {
            List<StateCensusData> highestDensity = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1382611", highestDensity.get(0).getDensityPerSqKm());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }

//    UC4
    @Test
    public void givenStateCensusCSTFileGetAreaMethod_whenProper_ReturnLargestAreaAndSortByAreaWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getAreaInSqKm");
        try {
            List<StateCensusData> largestArea = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("94163", largestArea.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFileWithGetAreaMethod__whenImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, JSONPath, STATE_CENSUS_DATA, "getArea");
        try {
            List<StateCensusData> largestArea = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1102", largestArea.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }

}


