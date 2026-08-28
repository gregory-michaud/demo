package fr.eni.ecole.demo.controller;

import fr.eni.ecole.demo.bll.EmployeService;
import fr.eni.ecole.demo.bo.Employe;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/eniecole/employes")
public class EmployeController {

    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<?> chargerTousEmployes(){
        List<Employe> listeEmploye = employeService.chargerTousEmployes();
        if(listeEmploye == null || listeEmploye.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listeEmploye);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> rechercherUnEmployeParId(@PathVariable("id") String id){

        try {
            Integer idEntier = Integer.parseInt(id);
            Employe employe = employeService.chargerUnEmployeParId(idEntier);
            return ResponseEntity.ok(employe);
        }catch (NumberFormatException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Votre identifiant n'est pas un entier");
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> ajoutEmploye(@RequestBody Employe employe){
        try {
            employeService.ajouter(employe);
            return ResponseEntity.ok(employe);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


}
