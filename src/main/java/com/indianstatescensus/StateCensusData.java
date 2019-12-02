package com.indianstatescensus;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

        @CsvBindByName
        private String StateName;

        @CsvBindByName(column = "Population", required = true)
        private String Population;

        @CsvBindByName(column = "AreaInSqKm")
        private String AreaInSqKm;

        @CsvBindByName
        private String DensityPerSqKm;

}
