package fr.eni.ecole.demo.dal;

import fr.eni.ecole.demo.bo.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
