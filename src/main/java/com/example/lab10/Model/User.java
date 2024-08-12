package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.SqlReturnType;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

//id ,must be generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    ▪ name: - Cannot be null.
//- Length must be more than 4 characters.
//- Must contain only characters (no numbers).
@NotEmpty(message = "Name should not be Empty!")
@Size(min=5)
@Pattern(regexp="^[a-zA-Z]*$",message ="Name Must contain only characters (no numbers)")
//@Column(columnDefinition = "varchar(20) not null unique  check (name regexp'^[a-zA-Z]*$')")
    private String name;


//email , - Must be a valid email format. - Must be unique.
@Email
@Column(columnDefinition = "varchar(30) not null unique")
    private String email;


// password - Cannot be null.
@NotEmpty(message = "Password should not be Empty!")
@Column(columnDefinition = "varchar(40) not null")
    private String password;


// age - Cannot be null. Must be a number.Must be more than 21.
@NotNull(message = "Age should not be Null!")
@Positive
@Min(value=22)
//@Column(columnDefinition = "int not null check(age > 21)")
    private int age;

//role - Cannot be null.- Must be either "JOB_SEEKER" or "EMPLOYER" only.
    @NotEmpty(message = "Role should not be Empty!")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$" , message = "Two valid inputs only, JOB_SEEKER or EMPLOYER!")
  //  @Column(columnDefinition = "varchar(20) not null check(role='JOB_SEEKER' or role='EMPLOYER')")
    private String role;



}
