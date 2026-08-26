package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilityRepository extends JpaRepository<Civilite, String> {
}
