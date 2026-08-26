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
@Table(name = "COURSE")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COURSE_TITLE", length = 100, nullable = false)
    private String titre;

    @Column(name = "COURSE_SECTOR", length = 100, nullable = false)
    private String filiere;


}
