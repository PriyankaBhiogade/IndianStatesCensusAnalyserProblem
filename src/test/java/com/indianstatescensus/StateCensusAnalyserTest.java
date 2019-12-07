package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class StateCensusAnalyserTest {
    String stateCensusFilePath = "StateCensusData.csv";
    String stateCodeFilePath = "StateCode.csv";
    String jsonPath = "StateCensus.json";
    String STATE_CENSUS_DATA = "com.indianstatescensus.StateCensusData";
    String wrongFileName = "StateCensusData123.csv";

    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnCount() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
        try {
            List<StateCensusData> recordCount = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals(29, recordCount.size());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenFileImProper_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(wrongFileName, jsonPath, STATE_CENSUS_DATA, "");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenExtensionIncorrect_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCodeFilePath, jsonPath, STATE_CENSUS_DATA, "");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenDelimiterIncorrect_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE,e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenHeaderIncorrect_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnTopNameSortByStateNameWriteIntoJSON() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "StateName");
        try {
            List<StateCensusData> topStateName = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("Andhra Pradesh", topStateName.get(0).getStateName());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenImProper_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "State");
        try {
            List<StateCensusData> topStateName = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("Andhra Pradesh", topStateName.get(0).getStateName());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSTFileGetPopulationMethod_whenProper_ReturnHighestPopulationAndSortByPopulationWriteIntoJSON() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Population");
        try {
            List<StateCensusData> highestPopulation = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("10116752", highestPopulation.get(0).getPopulation());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFileWithGetPopulationMethod_whenImProper_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Population123");
        try {
            List<StateCensusData> highestPopulation = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("10116752", highestPopulation.get(0).getPopulation());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSTFileGetDensityMethod_whenProper_ReturnHighestDensityAndSortByDensityWriteIntoJSON() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "DensityPerSqKm");
        try {
            List<StateCensusData> highestDensity = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1010", highestDensity.get(0).getDensityPerSqKm());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFileWithGetDensityMethod__whenImProper_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Density");
        try {
            List<StateCensusData> highestDensity = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1382611", highestDensity.get(0).getDensityPerSqKm());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenStateCensusCSTFileGetAreaMethod_whenProper_ReturnLargestAreaAndSortByAreaWriteIntoJSON() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "AreaInSqKm");
        try {
            List<StateCensusData> largestArea = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("10486", largestArea.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD,e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFileWithGetAreaMethod__whenImProper_ReturnCustomException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath, jsonPath, STATE_CENSUS_DATA, "Area");
        try {
            List<StateCensusData> largestArea = stateCensusAnalyser.readCensusRecord();
            Assert.assertEquals("1102", largestArea.get(0).getAreaInSqKm());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }
}


