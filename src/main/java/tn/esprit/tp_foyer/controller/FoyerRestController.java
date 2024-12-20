package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.service.IFoyerService;

import java.util.List;

@Tag(name = "Gestion des Foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")

public class FoyerRestController {

    IFoyerService iFoyerService;

    // http://localhost:8080/tpfoyer/foyer/retrieve_all_foyer
    @Operation(description = "Recupérer tous les Foyer de la base de données")
    @GetMapping("/retrieve_all_foyer")
    public List<Foyer> gatallFoyer(){
        List<Foyer> listFoyers = iFoyerService.retrieveAllFoyer();
        return listFoyers;
    }
    // http://localhost:8080/tpfoyer/foyer/retrieve_foyer_byid/{idFoyer}
    @GetMapping("/retrieve_foyer_byid/{idFoyer}")
    public Foyer retrieveFoyer(@PathVariable("idFoyer") Long idFoyer){
        Foyer foyerById = iFoyerService.retrieveFoyer(idFoyer);
        return foyerById;
    }

    // http://localhost:8080/tpfoyer/foyer/addfoyer
    @PostMapping("/addfoyer")
    public Foyer AddFoyer(@RequestBody Foyer foyer){
        Foyer addfoyer = iFoyerService.addFoyer(foyer);
        return addfoyer;
    }
    // http://localhost:8080/tpfoyer/foyer/update_foyer
    @PutMapping("/update_foyer/")
    public Foyer UpdateFoyer(@RequestBody Foyer foyer){
        Foyer updateFoyer = iFoyerService.updateFoyer(foyer);
        return updateFoyer;
    }

    // http://localhost:8080/tpfoyer/foyer/remove_foyer/{idFoyer}
    @DeleteMapping("/remove_foyer/{idFoyer}")
    public void DeleteFoyer(@PathVariable("idFoyer") Long idFoyer){
        iFoyerService.removeFoyer(idFoyer);
    }




}
