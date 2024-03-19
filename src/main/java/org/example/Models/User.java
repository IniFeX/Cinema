package org.example.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nickname")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;
}
