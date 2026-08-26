package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.DonneesPerso;
import fr.eni.ecole.demo.bo.EtudiantEni;
import fr.eni.ecole.demo.bo.Promo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestAssoOneToMany {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;


    @Test
    void test01_save(){

        Promo promo = Promo.builder()
                .nom("TEST01")
                .build();

        List<EtudiantEni> etudiantEnis = new ArrayList<>();
        for (int i = 1 ; i < 5 ; i++){

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("NOM"+i)
                    .prenom("Prenom"+i)
                    .build();

            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@gmail.com")
                    .immatriculation("IMMAT" + i)
                    .build();
            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);

            etudiantEnis.add(etudiantEni);
        }
        promo.setEtudiants(etudiantEnis);

        Promo promoDB = promoRepository.save(promo);

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        for (int i = 1 ; i <5 ; i++){
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("IMMAT"+i);
            Assertions.assertThat(optionalEtudiantEni).isPresent();
        }
    }

    @Test
    void test02_delete(){

        Promo promo = Promo.builder()
                .nom("TEST01")
                .build();

        List<EtudiantEni> etudiantEnis = new ArrayList<>();
        for (int i = 1 ; i < 5 ; i++){

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("NOM"+i)
                    .prenom("Prenom"+i)
                    .build();

            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@gmail.com")
                    .immatriculation("IMMAT" + i)
                    .build();
            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);

            etudiantEnis.add(etudiantEni);
        }
        promo.setEtudiants(etudiantEnis);

        Promo promoDB = promoRepository.save(promo);

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        for (int i = 1 ; i <5 ; i++){
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("IMMAT"+i);
            Assertions.assertThat(optionalEtudiantEni).isPresent();
        }

        Integer idPromo = promoDB.getId();

        promoRepository.delete(promoDB);

        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);
        Assertions.assertThat(optionalPromo).isEmpty();

        for (int i = 1 ; i <5 ; i++){
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("IMMAT"+i);
            Assertions.assertThat(optionalEtudiantEni).isEmpty();
        }
    }

    @Test
    void test03_orphanRemoval(){

        Promo promo = Promo.builder()
                .nom("TEST01")
                .build();

        List<EtudiantEni> etudiantEnis = new ArrayList<>();
        for (int i = 1 ; i < 5 ; i++){

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("NOM"+i)
                    .prenom("Prenom"+i)
                    .build();

            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@gmail.com")
                    .immatriculation("IMMAT" + i)
                    .build();
            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);

            etudiantEnis.add(etudiantEni);
        }
        promo.setEtudiants(etudiantEnis);

        Promo promoDB = promoRepository.save(promo);

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        for (int i = 1 ; i <5 ; i++){
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("IMMAT"+i);
            Assertions.assertThat(optionalEtudiantEni).isPresent();
        }

        Integer idPromo = promoDB.getId();
        promoDB.getEtudiants().clear();

        promoRepository.delete(promoDB);

        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);
        Assertions.assertThat(optionalPromo).isEmpty();

        for (int i = 1 ; i <5 ; i++){
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById("IMMAT"+i);
            Assertions.assertThat(optionalEtudiantEni).isEmpty();
        }
    }


}
