package fr.eni.ecole.demo.bll;

import fr.eni.ecole.demo.bo.Employe;
import fr.eni.ecole.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EmployeServiceImplTest {

    @Autowired
    private EmployeService employeService;

    @MockitoBean
    private EmployeRepository employeRepository;

    @Test
    void test_ajouterEmployeValide() {
        Employe employe = Employe.builder()
                .id(1)
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation("TEST_01")
                .build();

        // TODO :
        //Mockito.when(
        //        employeRepository.findByImmatriculation("TEST_01"))
        //        .thenReturn(Optional.empty());

        Mockito.when(
                        employeRepository.findById(1))
                .thenReturn(Optional.of(employe));

        employeService.ajouter(employe);

        Optional<Employe> optionalEmploye = employeRepository.findById(1);
        log.info(optionalEmploye.get().toString());

        Assertions.assertThat(optionalEmploye).isPresent();
    }

    @Test
    void test_ajouterEmployeSansNom() {
        Employe employe = Employe.builder()
                .id(1)
                .prenom("PrenomTest")
                .immatriculation("TEST_01")
                .build();

        assertThrows(RuntimeException.class,
                () -> employeService.ajouter(employe));
    }


    @Test
    void test_ajouterEmployeImmatExist() {
        Employe employe = Employe.builder()
                .id(1)
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation("TEST_01")
                .build();

        // TODO :
        //Mockito.when(
        //                employeRepository.findByImmatriculation("TEST_01"))
        //        .thenReturn(Optional.of(employe));

        assertThrows(RuntimeException.class,
                () -> employeService.ajouter(employe));
    }




}