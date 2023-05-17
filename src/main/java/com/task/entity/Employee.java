package com.task.entity;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Employee(String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;


    // Constructors, getters, setters
}
