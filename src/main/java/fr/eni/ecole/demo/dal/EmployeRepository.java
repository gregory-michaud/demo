package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {


    @Query("SELECT e FROM Employe e WHERE e.email = :email")
    Optional<Employe> findByEmailJPQL(@Param("email") String email);


    Optional<Employe> findByImmatriculation(@Param("immatriculation") String immatriculation);


}
