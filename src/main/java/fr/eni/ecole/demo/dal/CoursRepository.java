package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Integer> {
}
