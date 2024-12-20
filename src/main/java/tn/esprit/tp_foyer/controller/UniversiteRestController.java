package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.service.IUniversiteService;

import java.util.List;

@Tag(name = "Gestion des Universites")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")

public class UniversiteRestController {

    IUniversiteService iUniversiteService;

    // http://localhost:8080/tpfoyer/universite/retrieve_all_universite
    @Operation(description = "Recupérer tous les Foyer de la base de données")
    @GetMapping("/retrieve_all_universite")
    public List<Universite> gatallUniversite(){
        List<Universite> listUniversite = iUniversiteService.retrieveAllUniversit();
        return listUniversite;
    }
    // http://localhost:8080/tpfoyer/universite/retrieve_universite_byid/{idUniversite}
    @GetMapping("/retrieve_universite_byid/{idUniversite}")
    public Universite retrieveUniversite(@PathVariable("idFoyer") Long idUniversite){
        Universite universiteById = iUniversiteService.retrieveUniversite(idUniversite);
        return universiteById;
    }

    // http://localhost:8080/tpfoyer/universite/adduniversite
    @PostMapping("/adduniversite")
    public Universite AddUniversite(@RequestBody Universite universite){
        Universite adduniversite = iUniversiteService.addUniversite(universite);
        return adduniversite;
    }
    // http://localhost:8080/tpfoyer/universite/update_universite
    @PutMapping("/update_universite/")
    public Universite UpdateUniversite(@RequestBody Universite universite){
        Universite updateUniversite = iUniversiteService.updateUniversite(universite);
        return updateUniversite;
    }
    @PostMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(
            @PathVariable("idFoyer") Long idFoyer,
            @PathVariable("nomUniversite") String nomUniversite){
            Universite universite = iUniversiteService.affectFoyerAUniversite
                    (idFoyer,nomUniversite);
            return universite;
    }
    @PostMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(
            @PathVariable("idUniversite") Long idUniversite){
        Universite universite = iUniversiteService.desaffectFoyerUniversite
                (idUniversite);
        return universite;
    }


}
