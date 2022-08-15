package com.ayorinde.dronetechapi.dronetechapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // identity
    @Column(name = "name")
    private String name;
    @Column(name = "medicineWeight")
    private int medicineWeight;
    @Column(name = "code",unique = true)
    private String code;
    @Column(name = "medicationImage")
    private String medicationImageUrl;
}

