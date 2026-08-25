package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.EtudiantEni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantEniRepository extends JpaRepository<EtudiantEni, String> {
}
