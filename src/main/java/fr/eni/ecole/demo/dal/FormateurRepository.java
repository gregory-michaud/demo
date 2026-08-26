package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Integer> {
}
