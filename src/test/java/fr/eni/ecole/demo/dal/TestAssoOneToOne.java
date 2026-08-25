package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Adresse;
import fr.eni.ecole.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestAssoOneToOne {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Test
    void test01_save(){
        Employe e1 = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@gmail.fr")
                .immatriculation("IMMAT1")
                .numDom("0278984532")
                .numPortable("0687986545")
                .build();

        Adresse a1 = Adresse.builder()
                .rue("rue Test 1")
                .codePostal("44000")
                .ville("VILLE 1")
                .build();

        e1.setAdresse(a1);

        Employe employeDB = employeRepository.save(e1);
        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getAdresse().getId()).isGreaterThan(0);
    }

    @Test
    void test02_delete(){
        Employe e1 = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@gmail.fr")
                .immatriculation("IMMAT1")
                .numDom("0278984532")
                .numPortable("0687986545")
                .build();

        Adresse a1 = Adresse.builder()
                .rue("rue Test 1")
                .codePostal("44000")
                .ville("VILLE 1")
                .build();

        e1.setAdresse(a1);

        Employe employeDB = employeRepository.save(e1);
        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getAdresse().getId()).isGreaterThan(0);

        Integer idEmploye = employeDB.getId();
        Integer idAdresse = employeDB.getAdresse().getId();

        employeRepository.delete(employeDB);

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        Assertions.assertThat(optionalEmploye).isEmpty();

        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);
        Assertions.assertThat(optionalAdresse).isEmpty();


    }

    @Test
    void test03_orphanRemoval(){
        Employe e1 = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@gmail.fr")
                .immatriculation("IMMAT1")
                .numDom("0278984532")
                .numPortable("0687986545")
                .build();

        Adresse a1 = Adresse.builder()
                .rue("rue Test 1")
                .codePostal("44000")
                .ville("VILLE 1")
                .build();

        e1.setAdresse(a1);

        Employe employeDB = employeRepository.save(e1);
        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getAdresse().getId()).isGreaterThan(0);

        Integer idEmploye = employeDB.getId();
        Integer idAdresse = employeDB.getAdresse().getId();
        employeDB.setAdresse(null);

        employeRepository.delete(employeDB);

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);
        Assertions.assertThat(optionalEmploye).isEmpty();

        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);
        Assertions.assertThat(optionalAdresse).isEmpty();


    }



}
