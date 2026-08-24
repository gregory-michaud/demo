package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {


}
