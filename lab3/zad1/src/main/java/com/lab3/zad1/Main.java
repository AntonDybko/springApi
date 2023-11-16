package com.lab3.zad1;
import com.lab3.zad1.service.PersonService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.ApplicationContext;
import com.lab3.zad1.domain.Person;
import com.lab3.zad1.service.CsvReaderService;

import java.io.IOException;
import java.util.Map;
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class Main {
	/*public static void main(String[] args) {
		String csvFilePath = "C:\\Users\\AntonDybko\\aplikacje-web\\anton-dybko\\lab3\\zad1\\src\\main\\java\\com\\lab3\\zad1\\people.csv";

		CsvReaderService csvReaderService = new CsvReaderService();

		try {
			Map<String, Person> personMap = csvReaderService.readCsvFile(csvFilePath);

			for (Person person : personMap.values()) {
				System.out.println(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Main.class, args);

		PersonService personService = applicationContext.getBean(PersonService.class);
		System.out.println(personService.getPrezes());
		System.out.println(personService.getWicerezes());
		System.out.println(personService.getSekretarz());

		Person malenia = applicationContext.getBean("khasdfhasdf223", Person.class);
		Person radagon = applicationContext.getBean("khasdfhasdf224", Person.class);
		Person miquella = applicationContext.getBean("khasdfhasdf225", Person.class);
		System.out.println(malenia);
		System.out.println(radagon);
		System.out.println(miquella);
	}
}
