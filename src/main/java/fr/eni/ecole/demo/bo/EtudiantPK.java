package fr.eni.ecole.demo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EtudiantPK implements Serializable {

    private String email;

    private String immatriculation;
}
