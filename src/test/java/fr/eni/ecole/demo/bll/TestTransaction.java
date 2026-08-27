package fr.eni.ecole.demo.bll;

import fr.eni.ecole.demo.bo.Adresse;
import fr.eni.ecole.demo.bo.Employe;
import fr.eni.ecole.demo.dal.AdresseRepository;
import fr.eni.ecole.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class TestTransaction {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Test
    void test_ajout_employe_adresse_valide(){
        Employe e1 = Employe.builder()
                .nom("Nom1Test")
                .prenom("Prenom1Test")
                .email("email1@gmail.com")
                .immatriculation("IMMAT1")
                .numDom("0287986545")
                .numPortable("0687986545")
                .build();

        Adresse a1 = Adresse.builder()
                .rue("rue test 1")
                .codePostal("44000")
                .ville("VILLE1")
                .build();

        employeService.ajouterEmploye(e1, a1);

        log.info(e1.toString());
        log.info(a1.toString());

        Assertions.assertThat(e1.getId()).isGreaterThan(0);
        Assertions.assertThat(a1.getId()).isGreaterThan(0);


    }

    @Test
    void test_ajout_employe_adresse_invalide(){
        Employe e1 = Employe.builder()
                .nom("Nom2Test")
                .prenom("Prenom2Test")
                .email("email2@gmail.com")
                .immatriculation("IMMAT2")
                .numDom("0287986545")
                .numPortable("0687986545")
                .build();

        Adresse a1 = Adresse.builder()
                .rue("rue test 2")
                .ville("VILLE2")
                .build();


        assertThrows(RuntimeException.class,
                () -> employeService.ajouterEmploye(e1, a1));

        log.info(e1.toString());
        log.info(a1.toString());

        List<Employe> listeEmploye = employeRepository.findAll();
        Employe employeDB = listeEmploye
                .stream()
                .filter(e->e.getNom().equals("Nom2Test"))
                        .findAny().orElse(null);

        List<Adresse> listeAdresse = adresseRepository.findAll();
        Adresse adresseDB = listeAdresse
                .stream().filter(a->a.getVille().equals("VILLE2"))
                        .findAny().orElse(null);


        Assertions.assertThat(employeDB).isNull();
        Assertions.assertThat(adresseDB).isNull();
    }

}
