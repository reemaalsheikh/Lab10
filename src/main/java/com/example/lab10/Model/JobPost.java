package com.example.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

//@Table(name = "JobPosts")
public class JobPost {

//id, Must be Generated.
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;


    //title, Cannot be null, Length must be more than 4 characters.
    @NotEmpty(message = "Title should not be Empty!")
    @Size(min=5)
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    //description , Cannot be null.
    @NotEmpty(message = "Description should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String description;


    //location: Cannot be null.
    @NotEmpty(message = "location should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    //salary: Cannot be null, Must be a non-negative number
    @NotNull(message = "salary should not be Null!")
    @Positive
    @Column(columnDefinition = "DECIMAL(10,2) not null")
    private double salary;


    //postingDate
    @NotNull(message = "Posting Date should not be Empty!")
    @JsonFormat(pattern= "yyyy-MM-dd")
    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate postingDate;





}
