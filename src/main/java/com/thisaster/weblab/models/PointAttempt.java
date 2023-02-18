package com.thisaster.weblab.models;

import jakarta.persistence.*;
import lombok.*;

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
