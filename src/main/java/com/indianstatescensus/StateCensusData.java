package com.indianstatescensus;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

        @CsvBindByName(column = "StateName", required = true)
        private String StateName;

        @CsvBindByName(column = "Population", required = true)
        private String Population;

        @CsvBindByName(column = "AreaInSqKm", required = true)
        private String AreaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm", required = true)
        private String DensityPerSqKm;

}
