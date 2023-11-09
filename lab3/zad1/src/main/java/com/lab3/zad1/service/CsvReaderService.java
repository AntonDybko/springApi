package com.lab3.zad1.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.lab3.zad1.domain.Person;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CsvReaderService {
    public Map<String, Person> readCsvFile(String csvFilePath) throws IOException {
        Map<String, Person> personMap = new HashMap<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build()) {
                List<String[]> lines = csvReader.readAll();

            for (String[] line : lines) {
                String id = line[0];
                String firstName = line[1];
                String lastName = line[2];
                String email = line[3];
                int birthYear = Integer.parseInt(line[4]);
                Person person = new Person(id, firstName, lastName, email, birthYear);
                personMap.put(String.valueOf(id), person);
            }
        } catch (Exception e){

        }

        return personMap;
    }
}
