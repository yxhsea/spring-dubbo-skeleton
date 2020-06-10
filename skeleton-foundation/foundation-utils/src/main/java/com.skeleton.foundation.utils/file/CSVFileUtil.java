package com.skeleton.foundation.utils.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileUtil<T> {

    /**
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List readOneByOne(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        return  readOneByOne(reader);
    }

    /**
     * @param reader
     * @return
     * @throws IOException
     */
    public static List readOneByOne(Reader reader) throws IOException {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = new ArrayList<>();
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            records.add(nextRecord);
        }
        return  records;
    }

   public static <T> List<T> readToListOfObject(String filePath,Class objClass) throws IOException{
       Reader reader = Files.newBufferedReader(Paths.get(filePath));
       return readToListOfObject(reader,objClass);
   }

    /**
     * @param reader
     * @param objClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> readToListOfObject(Reader reader,Class objClass) throws IOException{
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                .withType(objClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean.parse();
    }


    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List readAllAtOnce(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = csvReader.readAll();
       return records;
    }

    /**
     *
     * @param filePath
     * @param headerRecord
     * @param records
     * @throws IOException
     */
    public static void writeFromArrayOfStrings(String filePath, String[] headerRecord,List<String[]> records ) throws IOException {
           Writer writer = Files.newBufferedWriter(Paths.get(filePath));
         try (  CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
          ) {
            csvWriter.writeNext(headerRecord);
            for (String[] record : records) {
                csvWriter.writeNext(record);
            }
        }
    }

    public static <T> void writeFromListOfObjects(String filePath ,List<T> list) throws IOException, CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath));
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(list);
    }


}
