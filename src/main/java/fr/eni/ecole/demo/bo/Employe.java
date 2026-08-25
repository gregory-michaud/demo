package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import lombok.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@Builder

@Entity
@Table(name = "EMPOYEE")
public class Employe {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMPLOYEE_NAME", length = 50, nullable = false)
    private String nom;

    @Column(name = "EMPLOYEE_FIRSTNAME", length = 50, nullable = false)
    private String prenom;

    @Column(name = "EMPLOYEE_EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "EMPLOYEE_REGISTATION", length = 100, nullable = false, unique = true)
    private String immatriculation;

    @Column(name = "EMPLOYEE_HOME_PHONE_NUMBER")
    private String numDom;
    @Column(name = "EMPLOYEE_CELL_PHONE_NUMBER")
    private String numPortable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

}
