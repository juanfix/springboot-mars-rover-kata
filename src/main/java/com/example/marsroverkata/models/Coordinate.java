package com.example.marsroverkata.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "x_position")
    private Integer XPosition;
    @Column(name = "y_position")
    private Integer YPosition;
}
