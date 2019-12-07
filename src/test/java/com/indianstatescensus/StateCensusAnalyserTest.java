package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StateCensusAnalyserTest {
    String stateCensusFilePath = "StateCensusData.csv";
    String jsonPath = "StateCensus.json";
    String STATE_CENSUS_DATA = "com.indianstatescensus.StateCensusData";

    //UC1
    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnCountAndSortByStateNameWriteIntoJSON() throws IOException, IllegalAccessException, InvocationTargetException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCensusData123.csv", jsonPath, STATE_CENSUS_DATA, "");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCode.csv", jsonPath, STATE_CENSUS_DATA, "");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "StateName");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "State");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Population");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Population123");
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
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "DensityPerSqKm");
    try {
        List<StateCensusData> highestDensity = stateCensusAnalyser.readCensusRecord();
        Assert.assertEquals("1010", highestDensity.get(0).getDensityPerSqKm());
    } catch (StateCensusAnalyserException | NoSuchMethodException e) {
        System.out.println(e.getMessage());
        Assert.assertEquals("File Not Found", e.getMessage());
    }
}

    @Test
    public void givenStateCensusCSVFileWithGetDensityMethod__whenImProper_ReturnCustomException() throws IOException, IllegalAccessException, InvocationTargetException {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Density");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "AreaInSqKm");
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
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Area");
        try {
            List<StateCensusData> largestArea = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1102", largestArea.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("METHOD NOT FOUND", e.getMessage());
        }
    }
}


