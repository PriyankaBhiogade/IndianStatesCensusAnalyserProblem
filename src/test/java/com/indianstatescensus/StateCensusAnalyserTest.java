package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class StateCensusAnalyserTest {
    String stateCodeFilePath = "StateCode.csv";
    String stateCensusFilePath = "StateCensusData.csv";

    //UC1
    @Test
    public void givenStateCSVFile_whenProper_ReturnTrue() throws StateCensusAnalyserException, IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCodeFilePath);
        try {
            int recordCount =  stateCensusAnalyser.readRecord();
            Assert.assertEquals(37, recordCount);
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCSVFile_whenImProper_ReturnCustomException() throws  IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCode123.csv");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCSVFile_whenExtensionIncorrect_ReturnCustomException() throws  IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCSVFile_whenDelimiterIncorrect_ReturnCustomException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCodeFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCSVFile_whenHeaderIncorrect_ReturnCustomException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCodeFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    //UC2
    @Test
    public void givenStateCensusCSVFile_whenProper_ReturnTrue() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath);
        try {
            int recordCount = stateCensusAnalyser.readCensusRecord();
             Assert.assertEquals(29, recordCount);
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenImProper_ReturnCustomException() throws  IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("StateCensusData123.csv");
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenExtensionIncorrect_ReturnCustomException() throws  IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCodeFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenDelimiterIncorrect_ReturnCustomException() throws  IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_whenHeaderIncorrect_ReturnCustomException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(stateCensusFilePath);
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readCensusRecord();
        } catch (StateCensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER",e.getMessage());
        }
    }
}
