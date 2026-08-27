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

@DataJpaTest
@Slf4j
public class TestHeritage {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private ChargeReeRepository chargeReeRepository;

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
    void test01_findEmploye(){
        List<Employe> employeList = employeRepository.findAll();

        log.info(employeList.toString());

        Assertions.assertThat(employeList).hasSize(3);
    }

    @Test
    void test02_findFormateur(){
        List<Formateur> formateurList = formateurRepository.findAll();
        log.info(formateurList.toString());
        Assertions.assertThat(formateurList).hasSize(1);
    }

    @Test
    void test03_findChargeRee(){
        List<ChargeRee> chargeReeList = chargeReeRepository.findAll();
        log.info(chargeReeList.toString());
        Assertions.assertThat(chargeReeList).hasSize(1);
    }


}
