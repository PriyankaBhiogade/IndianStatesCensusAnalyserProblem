package com.indianstatescensus;

import com.opencsv.bean.CsvBindByName;

public class StateCodeData {
    @CsvBindByName
    private String SrNo;

    @CsvBindByName(column = "StateName", required = true)
    private String StateName;

    @CsvBindByName(column = "StateCode")
    private String StateCode;

    @CsvBindByName
    private String TIN;
}
