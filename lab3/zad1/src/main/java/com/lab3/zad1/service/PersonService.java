package com.lab3.zad1.service;
import com.lab3.zad1.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
    private final Person prezes;
    private final Person wiceprezes;
    private final Person sekretarz;
    public PersonService(
            @Qualifier("prezes") Person prezes,
            @Qualifier("wiceprezes") Person wiceprezes,
            @Qualifier("sekretarz") Person sekretarz
    ) {
        this.prezes = prezes;
        this.wiceprezes = wiceprezes;
        this.sekretarz = sekretarz;
    }

    public Person getPrezes() {
        return prezes;
    }
    public Person getWicerezes() {
        return wiceprezes;
    }
    public Person getSekretarz() {
        return sekretarz;
    }
}
