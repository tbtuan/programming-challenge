package de.exxcellent.challenge.readers;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVFileReader<T> implements Reader {

    private Class<T> tClass;
    private BeanVerifier<T> beanVerifier;
    private String filename;

    /**
     * To set up the file repository it requires a
     * @param tClass The type of the mapped object T
     * @param beanVerifier A BeanVerifier<T> to filter out beans that do not satisfy the condition
     * @param filename The location of a file
     */
    public CSVFileReader(Class<T> tClass, BeanVerifier<T> beanVerifier, String filename) {
        this.tClass = tClass;
        this.beanVerifier = beanVerifier;
        this.filename = filename;
    }

    public List<T> readAll() {
        List<T> list = new LinkedList<>();
        try (java.io.Reader reader = new FileReader(filename)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(this.tClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifier(this.beanVerifier)
                    .withThrowExceptions(false)
                    .build();
            list = csvToBean.parse();
        } catch (IOException e) {
            System.out.println("File: \"" + filename + "\" could not be found");
        } finally {
            return list;
        }
    }

}
