package com.ug.edu.pl.ap.lab9.domain;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private final UUID id;
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    private String name;
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private double calories;
    @NotNull(message = "Field isVegetarian cannot be empty")
    private Boolean isVegetarian;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Field expirationDate cannot be empty")
    @FutureOrPresent(message = "The expiration date must be future or today")
    private LocalDate expirationDate ;
    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private License license;
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "food_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Category> categories;
}
