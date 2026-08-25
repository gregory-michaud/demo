package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Etudiant;
import fr.eni.ecole.demo.bo.EtudiantPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, EtudiantPK> {
}
