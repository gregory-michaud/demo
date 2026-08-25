package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.DonneesPerso;
import fr.eni.ecole.demo.bo.EtudiantEni;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestAssoOneToOneBi {

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private DonneesPersoRepository donneesPersoRepository;

    @Test
    void test01_save(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        EtudiantEni etudiantEniDB = etudiantEniRepository.save(e1);

        log.info(etudiantEniDB.toString());

        Assertions.assertThat(etudiantEniDB.getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(etudiantEniDB.getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(etudiantEniDB.getDonneesPerso().getId()).isGreaterThan(0);
    }


    @Test
    void test02_delete(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        EtudiantEni etudiantEniDB = etudiantEniRepository.save(e1);

        log.info(etudiantEniDB.toString());

        Assertions.assertThat(etudiantEniDB.getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(etudiantEniDB.getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(etudiantEniDB.getDonneesPerso().getId()).isGreaterThan(0);


        Integer idDonneesPerso = etudiantEniDB.getDonneesPerso().getId();
        String idEtudiantEni = etudiantEniDB.getImmatriculation();

        etudiantEniRepository.delete(etudiantEniDB);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(idEtudiantEni);
        Assertions.assertThat(optionalEtudiantEni).isEmpty();

        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);
        Assertions.assertThat(optionalDonneesPerso).isEmpty();



    }

    @Test
    void test03_orphanRemoval(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        EtudiantEni etudiantEniDB = etudiantEniRepository.save(e1);

        log.info(etudiantEniDB.toString());

        Assertions.assertThat(etudiantEniDB.getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(etudiantEniDB.getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(etudiantEniDB.getDonneesPerso().getId()).isGreaterThan(0);


        Integer idDonneesPerso = etudiantEniDB.getDonneesPerso().getId();
        String idEtudiantEni = etudiantEniDB.getImmatriculation();

        etudiantEniDB.setDonneesPerso(null);

        etudiantEniRepository.delete(etudiantEniDB);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(idEtudiantEni);
        Assertions.assertThat(optionalEtudiantEni).isEmpty();

        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);
        Assertions.assertThat(optionalDonneesPerso).isEmpty();



    }

    @Test
    void test04_saveDonneesPerso(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        DonneesPerso donneesPersoDB = donneesPersoRepository.save(dP1);

        log.info(donneesPersoDB.toString());

        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(donneesPersoDB.getId()).isGreaterThan(0);

        Integer idDonneesPerso = donneesPersoDB.getId();
        String idEtudiantEni = donneesPersoDB.getEtudiantEni().getImmatriculation();

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(idEtudiantEni);
        Assertions.assertThat(optionalEtudiantEni).isPresent();

        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);
        Assertions.assertThat(optionalDonneesPerso).isPresent();

    }

    @Test
    void test05_deleteDonneesPerso(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        DonneesPerso donneesPersoDB = donneesPersoRepository.save(dP1);

        log.info(donneesPersoDB.toString());

        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(donneesPersoDB.getId()).isGreaterThan(0);


        Integer idDonneesPerso = donneesPersoDB.getId();
        String idEtudiantEni = donneesPersoDB.getEtudiantEni().getImmatriculation();

        donneesPersoRepository.delete(donneesPersoDB);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(idEtudiantEni);
        Assertions.assertThat(optionalEtudiantEni).isEmpty();

        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);
        Assertions.assertThat(optionalDonneesPerso).isEmpty();



    }


    @Test
    void test06_orphaneRemovalDonneesPerso(){
        EtudiantEni e1 = EtudiantEni.builder()
                .immatriculation("IMMAT01")
                .email("email1@gmail.com")
                .build();

        DonneesPerso dP1 = DonneesPerso.builder()
                .nom("NomTest01")
                .prenom("PrenomTest01")
                .build();

        e1.setDonneesPerso(dP1);
        dP1.setEtudiantEni(e1);

        DonneesPerso donneesPersoDB = donneesPersoRepository.save(dP1);

        log.info(donneesPersoDB.toString());

        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getEmail()).isEqualTo(e1.getEmail());
        Assertions.assertThat(donneesPersoDB.getEtudiantEni().getImmatriculation()).isEqualTo(e1.getImmatriculation());
        Assertions.assertThat(donneesPersoDB.getId()).isGreaterThan(0);


        Integer idDonneesPerso = donneesPersoDB.getId();
        String idEtudiantEni = donneesPersoDB.getEtudiantEni().getImmatriculation();

        donneesPersoDB.setEtudiantEni(null);

        donneesPersoRepository.delete(donneesPersoDB);

        Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(idEtudiantEni);
        Assertions.assertThat(optionalEtudiantEni).isEmpty();

        Optional<DonneesPerso> optionalDonneesPerso = donneesPersoRepository.findById(idDonneesPerso);
        Assertions.assertThat(optionalDonneesPerso).isEmpty();



    }

}
