package com.indianstatescensus;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

        @CsvBindByName(column = "StateName", required = true)
        private String StateName;

        @CsvBindByName(column = "Population", required = true)
        private int Population;

        @CsvBindByName(column = "AreaInSqKm", required = true)
        private int AreaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm", required = true)
        private int DensityPerSqKm;

        public String getStateName() {
                return StateName;
        }

        public void setStateName(String stateName) {
                StateName = stateName;
        }

        public int getPopulation() {
                return Population;
        }

        public void setPopulation(int population) {
                Population = population;
        }

        public int getAreaInSqKm() {
                return AreaInSqKm;
        }

        public void setAreaInSqKm(int areaInSqKm) {
                AreaInSqKm = areaInSqKm;
        }

        public int getDensityPerSqKm() {
                return DensityPerSqKm;
        }

        public void setDensityPerSqKm(int densityPerSqKm) {
                DensityPerSqKm = densityPerSqKm;
        }
}
