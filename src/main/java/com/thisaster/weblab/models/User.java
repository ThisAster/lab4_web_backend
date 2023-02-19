package com.thisaster.weblab.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
