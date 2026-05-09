package com.philosophify.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor//default cnstr
@AllArgsConstructor//depd Inj
@Builder//lombok
public class Philosopher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    @Column(length = 500)
    private String shortBio;

    @Column(length = 2000)
    private String fullBio;

    @OneToMany(mappedBy = "philosopher", cascade = CascadeType.ALL)
    private List<Quote> quotes=new ArrayList<>();
}
