package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "TRAINER")
public class Formateur extends Employe {

    @Column(name = "COMPUTER_SCIENCE_COURSE", length = 100)
    private String filiere;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "TRAINER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")}
    )
    private @Builder.Default List<Cours> coursDispenses = new ArrayList<>();

}
