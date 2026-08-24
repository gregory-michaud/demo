package fr.eni.ecole.demo.bo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EmployeTest {

    @Test
    void test_createEmploye(){
        Employe employe = Employe.builder()
                .id(1)
                .nom("NomTest")
                .prenom("PrenomTest")
                .email("emailTest@test.fr")
                .immatriculation("IMMAT_TEST")
                .build();


        log.info(employe.toString());

        assertEquals(1, employe.getId());
        assertEquals("NomTest", employe.getNom());
    }
}