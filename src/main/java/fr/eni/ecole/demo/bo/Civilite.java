package fr.eni.ecole.demo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

@Entity
@Table(name = "CIVILITY")
public class Civilite {

    @Id
    @Column(name = "CIVILITY_ID", length = 5)
    private String clef;

    @Column(name = "CIVILITY_LABEL", length = 20, nullable = false)
    private String libelle;


}
