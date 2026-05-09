package com.philosophify.backend.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor//default cnstr
@AllArgsConstructor//depd Inj
@Builder//lombok
public class Concept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    @Column(length = 2000)
    private String explanation;

    private String example;

    public Concept(String title, String category, String explanation, String example) {
        this.title=title;
        this.category=category;
        this.explanation=explanation;
        this.example=example;
    }
}