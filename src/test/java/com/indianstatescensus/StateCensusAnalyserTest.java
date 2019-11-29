package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class StateCensusAnalyserTest {

    @Test
    public void givenStateCSVFile_whenProper_ReturnTrue() throws StateCensusAnalyserException, IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int recordCount = stateCensusAnalyser.readRecord();
        Assert.assertEquals(37, recordCount);
    }

    @Test
    public void givenStateCSVFile_whenImProper_ReturnCustomException() throws StateCensusAnalyserException, IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyserException.class);
            stateCensusAnalyser.readRecord();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("File Not Found", e.getMessage());
        }
    }
}
