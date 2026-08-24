package fr.eni.ecole.demo.bll;

import fr.eni.ecole.demo.bo.Employe;

import java.util.List;

public interface EmployeService {

    void ajouter(Employe e);

    List<Employe> chargerTousLesEmployes();



}
