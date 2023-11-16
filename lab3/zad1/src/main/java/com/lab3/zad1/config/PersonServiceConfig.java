package com.lab3.zad1.config;
import com.lab3.zad1.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersonServiceConfig {
    @Bean
    @Qualifier("prezes")
    @Scope("singleton")
    public Person getPrezes() {
        return new Person("ff45", "Jan", "Kowalski", "jan.kowalski@example.com", 1980);
    }
    @Bean
    @Qualifier("wiceprezes")
    @Scope("singleton")
    public Person getWiceprezes() {
        return new Person("ff46", "Anna", "Nowak", "anna.nowak@example.com", 1985);
    }
    @Bean
    @Qualifier("sekretarz")
    @Scope("singleton")
    public Person getSekretarz() {
        return new Person("ff47", "Bogdan", "Nowak", "zosia.nowak@example.com", 1990);
    }
}
