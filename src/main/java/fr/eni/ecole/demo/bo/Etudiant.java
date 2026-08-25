package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "STUDENT")
@IdClass(EtudiantPK.class)
public class Etudiant {

    @Id
    @Column(name = "STUDENT_EMAIL", length = 100, unique = true)
    private String email;

    @Id
    @Column(name = "STUDENT_REGISTRATION", length = 50, unique = true)
    private String immatriculation;

    @Column(name = "STUDENT_NAME", length = 100, nullable = false)
    private String nom;

    @Column(name = "STUDENT_FIRSTNAME", length = 100, nullable = false)
    private String prenom;

}
