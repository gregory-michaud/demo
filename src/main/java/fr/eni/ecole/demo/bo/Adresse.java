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
@Table(name = "ADDRESS")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;

    @Column(name = "ADDRESS_STREET", length = 100, nullable = false)
    private String rue;

    @Column(name = "ADDRESS_POSTAL_CODE", length = 50, nullable = false)
    private String codePostal;

    @Column(name = "ADDRESS_CITY", length = 100, nullable = false)
    private String ville;


}
