package tn.esprit.tp_foyer.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.service.IEtudiantService;

import java.util.List;

@Tag(name = "Gestion des Etudiant")
@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")

public class EtudiantRestController {

    IEtudiantService iEtudiantService;

    // http://localhost:8080/tpfoyer/etudiant/retrieve_all_etudiant
    @Operation(description = "Recupérer tous les retrieve de la base de données")
    @GetMapping("/retrieve_all_etudiant")
    public List<Etudiant> RetrieveEtudiant(){
        List<Etudiant> listEtudiants = iEtudiantService.retrieveAllEtudiant();
        return listEtudiants;
    }
    // http://localhost:8080/tpfoyer/etudiant/retrieve_etudiant_byid/{IdE}
    @GetMapping("/retrieve_etudiant_byid/{IdE}")
    public Etudiant retrieveEtudiant(@PathVariable("IdE") Long IdE){
        Etudiant EtudiantById = iEtudiantService.retrieveEtudiant(IdE);
        return EtudiantById;
    }

    // http://localhost:8080/tpfoyer/etudiant/addetudiant
    @PostMapping("/addetudiant")
    public List<Etudiant> AddEtudiant(@RequestBody Etudiant etudiant){
        List<Etudiant> addetudiant = iEtudiantService.addEtudiant((List<Etudiant>) etudiant);
        return addetudiant;
    }

    // http://localhost:8080/tpfoyer/etudiant/update_etudiant
    @PutMapping("/update_etudiant/")
    public Etudiant UpdateEtudiant(@RequestBody Etudiant etudiant){
        Etudiant updateEtudiant = iEtudiantService.updateEtudiant(etudiant);
        return updateEtudiant;
    }

    // http://localhost:8080/tpfoyer/etudiant/remove_etudiant/{IdE}
    @DeleteMapping("/remove_etudiant/{IdE}")
    public void DeleteEtudiant(@PathVariable("IdE") Long IdE){
        iEtudiantService.removeEtudiant(IdE);
    }

}
