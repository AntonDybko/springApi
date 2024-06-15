package com.ug.edu.pl.ap.lab9.domain;

import com.ug.edu.pl.ap.lab9.validation.ClosingTimeAfterOpeningTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ClosingTimeAfterOpeningTime
@EqualsAndHashCode
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "^[a-zA-Z, ]+$", message = "Wrong name format")
    @NotNull(message = "Field name cannot be empty")
    @Column(unique = true)
    private String name;
    @Pattern(regexp = "^(\\d+\\s)?([\\w\\s,.#-]+),\\s*([\\w\\s-]+),\\s*([A-Za-z]+),\\s*(\\d{5})(?:-(\\d{4}))?$"
            , message = "Please enter a valid address in the following format:\n" +
            "[Street Address], [City], [State], [ZIP Code]\n" +
            "Optional: [Apartment/Unit], [Extended ZIP Code]\n" +
            "Example: 123 Main St, Cityville, CA, 12345\n" +
            "Optional: Apt 4, 6789\n")
    @NotNull(message = "Field address cannot be empty")
    private String address;
    //456 Broad Ave, Townsville, AnotherState, 54321
    @Min(value = 0, message = "The number of calories must be greater than 0")
    private BigDecimal revenue;
    @NotNull(message = "Opening time cannot be null")
    private LocalTime openingTime;
    @NotNull(message = "Closing time cannot be null")
    private LocalTime closingTime;
    @OneToMany(
            //mappedBy = "shop",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.LAZY
    )
    //@JoinColumn(name = "food_id")
    private List<Food> food;
}

/*
@OneToMany(
            mappedBy = "shop",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.LAZY
    )
    private List<Food> food;
 */
