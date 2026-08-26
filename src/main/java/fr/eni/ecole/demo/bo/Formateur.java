package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "TRAINER")
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TRAINER_NAME", length = 100, nullable = false)
    private String nom;

    @Column(name = "TRAINER_FIRSTNAME", length = 100, nullable = false)
    private String prenom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "TRAINER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")}
    )
    private @Builder.Default List<Cours> coursDispenses = new ArrayList<>();

}
