package com.indianstatescensus;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyser<T extends Comparable<T>> {
    static String jsonPath;
    String csvFilePath;
    String methodName;
    String stateCensusClassName;

    public StateCensusAnalyser(String stateCensusFilePath, String jsonPath, String className,String sortBy) {
        this.csvFilePath = stateCensusFilePath;
        this.jsonPath = jsonPath;
        this.stateCensusClassName = className;
        this.methodName = sortBy;
    }

    public List<StateCensusData> readCensusRecord() throws IOException, StateCensusAnalyserException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        ) {
            CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<StateCensusData> stateCensusData = csvToBean.parse();
            if(methodName.length() > 0) {
                this.SortState(stateCensusData, methodName);
                return stateCensusData;
            }
            else {
                return stateCensusData;
            }
        }catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, "File Not Found", ex);
        }catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, "ERROR IN FILE TYPE OR IN FILE DELIMITER OR IN FILE HEADER OR METHOD NOT FOUND", e);
        }catch (NoSuchMethodException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, "METHOD NOT FOUND", e);
        }
    }

    public void SortState(List<StateCensusData> csvCensusData, String methodName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        for (int i = 0; i < csvCensusData.size() - 1; i++) {
            for (int j = 0; j < csvCensusData.size() - i - 1; j++) {
                Class getClass1 = csvCensusData.get(j).getClass();
                Method methodCall1 = getClass1.getDeclaredMethod("get"+methodName);
                T listData1 = (T) methodCall1.invoke(csvCensusData.get(j));
                Class getClass2 = csvCensusData.get(j + 1).getClass();
                Method methodCall2 = getClass2.getDeclaredMethod("get"+methodName);
                T listData2 = (T) methodCall2.invoke(csvCensusData.get(j + 1));
                if (listData1.compareTo(listData2) > 0) {
                    StateCensusData temp = csvCensusData.get(j);
                    csvCensusData.set(j, csvCensusData.get(j + 1));
                    csvCensusData.set(j + 1, temp);
                }
            }
        }
        convertCSVtoJSON(csvCensusData);
    }

    public static void convertCSVtoJSON(List<StateCensusData> stateCensusData) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(stateCensusData);
        FileWriter fileWriter = new FileWriter(jsonPath);
        fileWriter.write(json);
        fileWriter.close();
    }
}
