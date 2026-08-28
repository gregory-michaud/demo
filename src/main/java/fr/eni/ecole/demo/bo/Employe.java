package fr.eni.ecole.demo.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@SuperBuilder

@Entity
@Table(name = "EMPOYEE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMPLOYEE_NAME", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String nom;

    @Column(name = "EMPLOYEE_FIRSTNAME", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String prenom;

    @Column(name = "EMPLOYEE_EMAIL", length = 100, nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "EMPLOYEE_REGISTATION", length = 100, nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String immatriculation;

    @Column(name = "EMPLOYEE_HOME_PHONE_NUMBER")
    private String numDom;
    @Column(name = "EMPLOYEE_CELL_PHONE_NUMBER")
    private String numPortable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CIVILITY_ID")
    @NotNull
    private Civilite civilite;

}
