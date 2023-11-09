package com.lab3.zad1;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lab3.zad1.domain.Person;
import com.lab3.zad1.service.CsvReaderService;

import java.io.IOException;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String csvFilePath = "C:\\Users\\AntonDybko\\aplikacje-web\\anton-dybko\\lab3\\zad1\\src\\main\\java\\com\\lab3\\zad1\\people.csv"; // Zastąp ścieżką do pliku CSV

		CsvReaderService csvReaderService = new CsvReaderService();

		try {
			Map<String, Person> personMap = csvReaderService.readCsvFile(csvFilePath);

			for (Person person : personMap.values()) {
				System.out.println(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
