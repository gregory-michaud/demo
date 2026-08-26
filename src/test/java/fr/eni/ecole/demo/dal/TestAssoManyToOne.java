package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Civilite;
import fr.eni.ecole.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestAssoManyToOne {

    @Autowired
    private CivilityRepository civilityRepository;

    @Autowired
    private EmployeRepository employeRepository;

    private Civilite monsieurDB;
    private Civilite madameDB;

    @BeforeEach
    void initCivilite(){
        Civilite monsieur = Civilite.builder()
                .clef("M")
                .libelle("Monsieur")
                .build();

        Civilite madame = Civilite.builder()
                .clef("Mme")
                .libelle("Madame")
                .build();

        monsieurDB = civilityRepository.save(monsieur);
        madameDB = civilityRepository.save(madame);
    }

    @Test
    void test01_save(){
        Employe e1 = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .immatriculation("IMMAT1")
                .email("email1@gmail.com")
                .civilite(monsieurDB)
                .build();

        Employe employeDB = employeRepository.save(e1);
        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getCivilite()).isEqualTo(monsieurDB);

        List<Civilite> listeCivilite = civilityRepository.findAll();
        Assertions.assertThat(listeCivilite).hasSize(2);
    }

    @Test
    void test02_delete(){
        Employe e1 = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .immatriculation("IMMAT1")
                .email("email1@gmail.com")
                .civilite(monsieurDB)
                .build();

        Employe employeDB = employeRepository.save(e1);
        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getCivilite()).isEqualTo(monsieurDB);

        List<Civilite> listeCivilite = civilityRepository.findAll();
        Assertions.assertThat(listeCivilite).hasSize(2);

        Integer idEmploye = employeDB.getId();

        employeRepository.delete(employeDB);

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        Assertions.assertThat(optionalEmploye).isEmpty();
        listeCivilite = civilityRepository.findAll();
        Assertions.assertThat(listeCivilite).hasSize(2);



    }



}
