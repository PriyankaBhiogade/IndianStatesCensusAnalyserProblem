package com.indianstatescensus;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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

    public StateCensusAnalyser(String stateCensusFilePath, String jsonPath, String className, String sortBy) {
        this.csvFilePath = stateCensusFilePath;
        this.jsonPath = jsonPath;
        this.stateCensusClassName = className;
        this.methodName = sortBy;
    }

    public List<StateCensusData> readCensusRecord() throws StateCensusAnalyserException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        ) {
            CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<StateCensusData> stateCensusData = csvToBean.parse();
            if (methodName.length() > 0) {
                this.sortState(stateCensusData, methodName);
            }
            return stateCensusData;
        } catch (NoSuchFileException ex) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, ex);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_INPUT_FILE, e);
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e);
        }
    }

    public void sortState(List<StateCensusData> csvCensusData, String methodName) throws StateCensusAnalyserException  {
        try {
            for (int i = 0; i < csvCensusData.size() - 1; i++) {
                for (int j = 0; j < csvCensusData.size() - i - 1; j++) {
                    Class getClass1 = csvCensusData.get(j).getClass();
                    Method methodCall1 = null;
                    methodCall1 = getClass1.getDeclaredMethod("get" + methodName);
                    T listData1 = (T) methodCall1.invoke(csvCensusData.get(j));
                    Class getClass2 = csvCensusData.get(j + 1).getClass();
                    Method methodCall2 = getClass2.getDeclaredMethod("get" + methodName);
                    T listData2 = (T) methodCall2.invoke(csvCensusData.get(j + 1));
                    if (listData1.compareTo(listData2) > 0) {
                        StateCensusData temp = csvCensusData.get(j);
                        csvCensusData.set(j, csvCensusData.get(j + 1));
                        csvCensusData.set(j + 1, temp);
                    }
                }
            }
            convertCSVtoJSON(csvCensusData);
        } catch (NoSuchMethodException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_METHOD, e);
        }catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.IO_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.ILLEGAl_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NULL_VALUE_FOUND, e);
        }
    }

    public static void convertCSVtoJSON(List<StateCensusData> stateCensusData) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(stateCensusData);
        FileWriter fileWriter = new FileWriter(jsonPath);
        fileWriter.write(json);
        fileWriter.close();
    }
}
