package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Cours;
import fr.eni.ecole.demo.bo.Formateur;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestAssoManyToMany {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    private List<Cours> listeCours = new ArrayList<>();

    @BeforeEach
    void initCours(){

        Cours c1 = Cours.builder()
                .titre("Java Spring Boot")
                .filiere("Dev")
                .build();

        listeCours.add(coursRepository.save(c1));

        Cours c2 = Cours.builder()
                .titre("PHP")
                .filiere("Dev")
                .build();

        listeCours.add(coursRepository.save(c2));

        Cours c3 = Cours.builder()
                .titre("Bases des réseaux")
                .filiere("Systemes et réseaux")
                .build();

        listeCours.add(coursRepository.save(c3));
    }

    @Test
    void test01_save(){
        Formateur f1 = Formateur.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .build();

        f1.getCoursDispenses().add(listeCours.get(0));
        f1.getCoursDispenses().add(listeCours.get(1));

        Formateur fDB = formateurRepository.save(f1);
        log.info(fDB.toString());
        Assertions.assertThat(fDB.getId()).isGreaterThan(0);
        Assertions.assertThat(fDB.getCoursDispenses()).hasSize(2);

    }

    @Test
    void test02_delete(){
        Formateur f1 = Formateur.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .build();

        f1.getCoursDispenses().add(listeCours.get(0));
        f1.getCoursDispenses().add(listeCours.get(1));

        Formateur fDB = formateurRepository.save(f1);
        log.info(fDB.toString());
        Assertions.assertThat(fDB.getId()).isGreaterThan(0);
        Assertions.assertThat(fDB.getCoursDispenses()).hasSize(2);

        Integer idFormateur = fDB.getId();

        formateurRepository.delete(fDB);

        Optional<Formateur> optionalFormateur = formateurRepository.findById(idFormateur);
        Assertions.assertThat(optionalFormateur).isEmpty();

        List<Cours> coursList = coursRepository.findAll();
        Assertions.assertThat(coursList).hasSize(3);

    }
    
}
