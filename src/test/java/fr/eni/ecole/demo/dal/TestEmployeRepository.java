package fr.eni.ecole.demo.dal;


import fr.eni.ecole.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEmployeRepository {

    @Autowired
    private EmployeRepository employeRepository;

    @Test
    void test01_save(){
        Employe employe = Employe.builder()
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation("IMMAT_01")
                .email("email01@gmail.com")
                .numDom("0212458798")
                .numPortable("0687986545")
                .build();

        Employe employeDB = employeRepository.save(employe);
        log.info(employeDB.toString());
        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
    }


    @Test
    void test02_findById(){
        Employe employe = Employe.builder()
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation("IMMAT_01")
                .email("email01@gmail.com")
                .numDom("0212458798")
                .numPortable("0687986545")
                .build();

        Employe employeDB = employeRepository.save(employe);
        log.info(employeDB.toString());
        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);

        Integer idEmploye = employeDB.getId();

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);

        Assertions.assertThat(optionalEmploye).isPresent();
        log.info(optionalEmploye.get().toString());



    }

}
