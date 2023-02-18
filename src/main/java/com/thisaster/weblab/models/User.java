package com.thisaster.weblab.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

}
