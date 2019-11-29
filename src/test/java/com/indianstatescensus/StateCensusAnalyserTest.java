package com.indianstatescensus;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    @Test
        public void givenStateCSVFile_whenProper_ReturnTrue(){
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int recordCount = stateCensusAnalyser.readRecord();
        Assert.assertEquals(37,recordCount);
    }

}
