package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"etudiantEni"})
@EqualsAndHashCode(exclude = {"etudiantEni"})
@Builder
@Entity
@Table(name = "STUDENT_DATA")
public class DonneesPerso {

    @Id
    @Column(name = "DATA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String nom;

    @Column(name = "FIRSTNAME", nullable = false, length = 100)
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "donneesPerso")
    private EtudiantEni etudiantEni;

}
