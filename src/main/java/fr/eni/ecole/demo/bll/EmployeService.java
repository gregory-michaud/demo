package fr.eni.ecole.demo.bll;

import fr.eni.ecole.demo.bo.Adresse;
import fr.eni.ecole.demo.bo.Employe;

import java.util.List;


public interface EmployeService {
	void ajouter(Employe employe);

	List<Employe> chargerTousEmployes();
	
	Employe chargerUnEmployeParId(int id);
	
	//Pour valider les transactions
	void ajouterEmploye(Employe employe, Adresse adresse) ;
}
