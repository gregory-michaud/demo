package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.ChargeRee;
import fr.eni.ecole.demo.bo.Employe;
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
public class TestJPQL {

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    void initDB(){
        List<Employe> employeList = new ArrayList<>();

        employeList.add(Employe.builder()
                .nom("Nom1")
                .prenom("Prenom1")
                .email("email1@gmail.com")
                .immatriculation("IMMAT1")
                .build());

        employeList.add(Formateur.builder()
                .nom("Nom2")
                .prenom("Prenom2")
                .email("email2@gmail.com")
                .immatriculation("IMMAT2")
                .filiere("Développement")
                .build());

        employeList.add(ChargeRee.builder()
                .nom("Nom3")
                .prenom("Prenom3")
                .email("email3@gmail.com")
                .immatriculation("IMMAT3")
                .numeroBureau("BUR1")
                .build());

        employeRepository.saveAll(employeList);
    }

    @Test
    void test01_findByEmailJPQL() {

        Optional<Employe> optionalEmploye = employeRepository.findByEmailJPQL("email3@gmail.com");

        Assertions.assertThat(optionalEmploye).isPresent();

        log.info(optionalEmploye.get().toString());
    }

    @Test
    void test02_findByImmatriculation() {

        Optional<Employe> optionalEmploye = employeRepository.findByImmatriculation("IMMAT2");

        Assertions.assertThat(optionalEmploye).isPresent();

        log.info(optionalEmploye.get().toString());
    }

}
