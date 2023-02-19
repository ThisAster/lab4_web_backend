package com.thisaster.weblab.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "point_attempts")
public class PointAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double x;
    private double y;
    private double r;
    @Column(name="attempt_time", nullable = false)
    private long attemptTime;
    @Column(name="process_time", nullable = false)
    private double processTime;
    @Column(nullable = false)
    private boolean success;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
}
