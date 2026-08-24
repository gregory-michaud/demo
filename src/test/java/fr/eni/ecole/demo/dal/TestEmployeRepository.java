package fr.eni.ecole.demo.dal;


import fr.eni.ecole.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

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

}
