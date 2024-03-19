package org.example.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "directors")
public class Director {
    @Id
    @Column(name = "director_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @OneToMany(mappedBy = "id")
    private List<Film> films;
}
