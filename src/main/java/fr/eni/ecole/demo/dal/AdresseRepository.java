package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
}
