package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "STUDENT_CLASS")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "STUDENT_CLASS_NAME", length = 100, nullable = false)
    private String nom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_CLASS_ID")
    private @Builder.Default List<EtudiantEni> etudiants = new ArrayList<>();


}
