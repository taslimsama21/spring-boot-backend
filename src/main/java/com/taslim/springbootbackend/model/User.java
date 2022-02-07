package com.taslim.springbootbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Enter a valid email!")
    @NotBlank(message = "Email is mandatory")
    @Size(min = 5, max = 140, message = "Email must have between {min} and {max} characters.")
    @Column(name = "email",unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 5, max = 140, message = "Password must have between {min} and {max} characters.")
    @Column(name = "password",unique = true, length = 60)
    private String password;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "company_name")
    private String companyName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
