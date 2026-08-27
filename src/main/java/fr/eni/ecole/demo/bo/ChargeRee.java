package fr.eni.ecole.demo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "SCHOOL_BUSINESS_OFFICER")
public class ChargeRee extends Employe {

    @Column(name = "OFFICE_NUMBER", length = 10)
    private String numeroBureau;
}
