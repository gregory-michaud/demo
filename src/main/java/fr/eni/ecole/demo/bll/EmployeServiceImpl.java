package fr.eni.ecole.demo.bll;

import fr.eni.ecole.demo.bo.Adresse;
import fr.eni.ecole.demo.bo.Employe;
import fr.eni.ecole.demo.dal.AdresseRepository;
import fr.eni.ecole.demo.dal.EmployeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private EmployeRepository employeRepository;

    private AdresseRepository adresseRepository;

    @Override
    public void ajouter(Employe e) {
        // Règles de validation
        if(e == null){
            throw new RuntimeException("L'employé ne doit pas être null");
        }
        if(e.getNom().isBlank()){
            throw  new RuntimeException("Le nom de l'employé est obligatoire");
        }
        // TODO RG : rechercher employé par immatriculation
        //  Optional<Employe> optionalEmploye = employeRepository.findByImmatriculation(e.getImmatriculation());
        //if(optionalEmploye.isPresent()){
        //    throw new RuntimeException("L'immatriculation doit être unique");
        //}
        employeRepository.save(e);
    }

    @Override
    public List<Employe> chargerTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Transactional
    @Override
    public void ajouter(Employe e, Adresse adresse) {
        // Règles de validation
        if(e == null){
            throw new RuntimeException("L'employé ne doit pas être null");
        }
        if(e.getNom().isBlank()){
            throw  new RuntimeException("Le nom de l'employé est obligatoire");
        }
        employeRepository.save(e);

        // RG de l'adresse
        if(adresse == null){
            throw new RuntimeException("L'adresse ne doit pas être null");
        }
        if(adresse.getRue().isBlank()){
            throw new RuntimeException("La rue est obligatoire");
        }
        adresseRepository.save(adresse);

    }
}
