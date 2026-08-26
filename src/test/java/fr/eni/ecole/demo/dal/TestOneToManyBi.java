package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.DonneesPerso;
import fr.eni.ecole.demo.bo.EtudiantEni;
import fr.eni.ecole.demo.bo.Promo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestOneToManyBi {


    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Test
    public void test_save() {
        final Promo promo = Promo
                .builder()
                .nom("EDWM_TEST")
                .build();

        // Association Bidirectionnelle
        List<EtudiantEni> etudiants = jeuDeDonnees();
        promo.setEtudiants(etudiants);

        etudiants.forEach(etudiant -> {
            etudiant.setPromo(promo);
        });


        // Appel du comportement
        final Promo promoDB = promoRepository.save(promo);
        // Vérification de l'identifiant
        assertThat(promoDB.getId()).isGreaterThan(0);

        // Vérification de la cascade de l'association
        assertThat(promoDB.getEtudiants()).isNotNull();
        assertThat(promoDB.getEtudiants()).isNotEmpty();
        assertThat(promoDB.getEtudiants().size()).isEqualTo(3);
        log.info(promoDB.toString());
    }

    @Test
    public void test_delete() {
        final Promo promo = Promo
                .builder()
                .nom("EDWM_TEST")
                .build();

        // Association OneToMany Bidirectionnelle
        final List<EtudiantEni> etudiants = jeuDeDonnees();
        promo.setEtudiants(etudiants);
        etudiants.forEach(etudiant -> {
            etudiant.setPromo(promo);
        });

        // Contexte de la DB
        final Promo promoDB = promoRepository.save(promo);
        assertThat(promoDB.getId()).isGreaterThan(0);
        assertThat(promoDB.getEtudiants()).isNotNull();
        assertThat(promoDB.getEtudiants()).isNotEmpty();
        List<EtudiantEni> etudiantsDB = promoDB.getEtudiants();
        List<String> listeIdEtudiantEniDB = etudiantsDB
                .stream()
                .map(EtudiantEni::getImmatriculation)
                .collect(Collectors.toList());

        Integer idPromo = promoDB.getId();
        // Appel du comportement
        promoRepository.delete(promoDB);

        // Vérification que l'entité a été supprimée
        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);
        assertThat(optionalPromo).isEmpty();
        // Vérifier que tous les EtudiantEni sont supprimés par cascade
        assertThat(listeIdEtudiantEniDB).isNotNull();
        assertThat(listeIdEtudiantEniDB).isNotEmpty();
        listeIdEtudiantEniDB.forEach(etudiantId -> {
            assertThat(etudiantId).isNotNull();
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(etudiantId);
            assertThat(optionalEtudiantEni).isEmpty();
        });
    }

    @Test
    public void test_orphanRemoval() {
        final Promo promo = Promo
                .builder()
                .nom("EDWM_TEST")
                .build();





        // Association OneToMany Bidirectionnelle
        final List<EtudiantEni> etudiants = jeuDeDonnees();
        promo.setEtudiants(etudiants);
        etudiants.forEach(etudiant -> {
            etudiant.setPromo(promo);
        });

        // Contexte de la DB
        final Promo promoDB = promoRepository.save(promo);
        assertThat(promoDB.getId()).isGreaterThan(0);
        assertThat(promoDB.getEtudiants()).isNotNull();
        assertThat(promoDB.getEtudiants()).isNotEmpty();
        List<EtudiantEni> etudiantsDB = promoDB.getEtudiants();
        List<String> listeIdEtudiantEniDB = etudiantsDB
                .stream()
                .map(EtudiantEni::getImmatriculation)
                .collect(Collectors.toList());

        // Détacher les employés de leur promo
        promoDB.getEtudiants().clear();

        Integer idPromo = promoDB.getId();

        // Appel du comportement
        promoRepository.delete(promoDB);

        // Vérification que l'entité a été supprimée
        Optional<Promo> optionalPromo = promoRepository.findById(idPromo);
        assertThat(optionalPromo).isEmpty();

        // Vérifier que tous les EtudiantEni sont supprimés par orphanRemoval
        assertThat(listeIdEtudiantEniDB).isNotNull();
        assertThat(listeIdEtudiantEniDB).isNotEmpty();
        listeIdEtudiantEniDB.forEach(etudiantId -> {
            assertThat(etudiantId).isNotNull();
            Optional<EtudiantEni> optionalEtudiantEni = etudiantEniRepository.findById(etudiantId);
            assertThat(optionalEtudiantEni).isEmpty();
        });
    }

    private List<EtudiantEni> jeuDeDonnees() {
        final List<EtudiantEni> etudiants = new ArrayList<>();
        String immatriculation = "IMMAT";

        for (int i = 1; i < 4; i++) {
            final DonneesPerso donneesPerso = DonneesPerso
                    .builder()
                    .nom("Nom" + i)
                    .prenom("Prenom" + i)
                    .build();
            final EtudiantEni etudiant = EtudiantEni
                    .builder()
                    .immatriculation(immatriculation + i)
                    .email("pnom" + i + "@campus-eni.fr")
                    .build();
            etudiant.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiant);
            etudiants.add(etudiant);
        }
        return etudiants;
    }

}
