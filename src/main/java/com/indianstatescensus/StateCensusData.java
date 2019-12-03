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

        public String getStateName() {
                return StateName;
        }

        public void setStateName(String stateName) {
                StateName = stateName;
        }

        public String getPopulation() {
                return Population;
        }

        public void setPopulation(String population) {
                Population = population;
        }

        public String getAreaInSqKm() {
                return AreaInSqKm;
        }

        public void setAreaInSqKm(String areaInSqKm) {
                AreaInSqKm = areaInSqKm;
        }

        public String getDensityPerSqKm() {
                return DensityPerSqKm;
        }

        public void setDensityPerSqKm(String densityPerSqKm) {
                DensityPerSqKm = densityPerSqKm;
        }
}
