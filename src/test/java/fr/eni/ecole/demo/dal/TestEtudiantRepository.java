package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Etudiant;
import fr.eni.ecole.demo.bo.EtudiantPK;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEtudiantRepository {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    void test01_save(){
        Etudiant e1 = Etudiant.builder()
                .email("email1@gmail.fr")
                .immatriculation("IMMAT1")
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .build();

        etudiantRepository.save(e1);

        EtudiantPK pk = EtudiantPK.builder()
                .email(e1.getEmail())
                .immatriculation(e1.getImmatriculation())
                .build();

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(pk);

        Assertions.assertThat(optionalEtudiant).isPresent();
        log.info(optionalEtudiant.get().toString());


    }


}
