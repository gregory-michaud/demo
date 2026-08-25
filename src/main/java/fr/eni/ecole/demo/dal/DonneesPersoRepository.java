package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.DonneesPerso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonneesPersoRepository extends JpaRepository<DonneesPerso, Integer> {
}
