package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "STUDENT_ENI")
public class EtudiantEni {

    @Id
    @Column(name = "REGISTRATION", length = 10)
    private String immatriculation;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DATA_ID")
    private DonneesPerso donneesPerso;

}
