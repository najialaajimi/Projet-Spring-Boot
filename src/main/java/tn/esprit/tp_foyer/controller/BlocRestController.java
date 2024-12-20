package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.service.IBlocService;

import java.util.List;

@Tag(name = "Gestion de Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")

public class BlocRestController {
    @Autowired
    IBlocService iBlocService;

    // http://localhost:8080/tpfoyer/bloc/retrieve_all_bloc
    @Operation(description = "Recupérer tous les BLoc de la base de données")
    @GetMapping("/retrieve_all_bloc")
    public List<Bloc> gatallBloc(){
        List<Bloc> listBlocs = iBlocService.retrieveAllBloc();
        return listBlocs;
    }
    // http://localhost:8080/tpfoyer/bloc/retrieve_bloc_byid/{idbloc}
    @GetMapping("/retrieve_bloc_byid/{idBloc}")
    public Bloc retrieveBloc(@PathVariable("idBloc") Long idbloc){
        Bloc blocById = iBlocService.retrieveBloc(idbloc);
        return blocById;
    }
    // http://localhost:8080/tpfoyer/bloc/aadbloc
    @PostMapping("/addbloc")
    public Bloc AddBloc(@RequestBody Bloc bloc){
        Bloc addbloc = iBlocService.addBloc(bloc);
        return addbloc;
    }
    // http://localhost:8080/tpfoyer/bloc/updatebloc
    @PutMapping("/updatebloc/")
    public Bloc UpdateBloc(@RequestBody Bloc bloc){
        Bloc updatebloc = iBlocService.updateBloc(bloc);
        return updatebloc;
    }

    // http://localhost:8080/tpfoyer/bloc/removebloc/{idbloc}
    @DeleteMapping("/removebloc/{idbloc}")
    public void DeleteBloc(@PathVariable("idbloc") Long idbloc){
        iBlocService.removeBloc(idbloc);
    }


}
