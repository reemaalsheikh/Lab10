package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name = "JobApplications")
public class JobApplication {

    //id, Must be Generated.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //userId, Cannot be null.
    @NotNull(message = "User Id should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private Integer userId;


    // jobPostId: Cannot be null.
    @NotNull(message = "Jop Post Id should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private Integer jopPostId;


}
