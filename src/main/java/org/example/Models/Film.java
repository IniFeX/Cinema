package org.example.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director director;
    @Column(name = "name")
    private String name;
    @Column(name = "issued_at")
    private Date issuedAt;
    @ManyToMany
    @JoinTable(
            name = "actor_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                '}';
    }
}
