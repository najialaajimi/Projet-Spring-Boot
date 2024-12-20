package tn.esprit.tp_foyer.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Reservation;
import tn.esprit.tp_foyer.service.IFoyerService;
import tn.esprit.tp_foyer.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion des Reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")

public class ResevationRestController {
    IReservationService iReservationService;

    // http://localhost:8080/tpfoyer/reservation/retrieve_all_reservation
    @Operation(description = "Recupérer tous les Reservations de la base de données")
    @GetMapping("/retrieve_all_reservation")
    public List<Reservation> gatallReservation(){
        List<Reservation> listReservations = iReservationService.retrieveAllReservation();
        return listReservations;
    }
    // http://localhost:8080/tpfoyer/reservation/retrieve_reservation_byid/{res}
    @GetMapping("/retrieve_reservation_byid/{idRes}")
    public Reservation retrieveReservation(@PathVariable("idRes") Long idRes){
        Reservation reservationById = iReservationService.retrieveReservation(idRes);
        return reservationById;
    }

    // http://localhost:8080/tpfoyer/reservation/update_reservation
    @PutMapping("/update_reservation/")
    public Reservation UpdateReservation(@RequestBody Reservation res){
        Reservation updateReservation = iReservationService.updateReservation(res);
        return updateReservation;
    }


}
