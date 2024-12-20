package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.entity.Reservation;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;
import tn.esprit.tp_foyer.repository.EtudiantRepository;
import tn.esprit.tp_foyer.repository.ReservationRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor

public class ReservationServiceImpl implements IReservationService {

    ReservationRepository reservationRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private BlocRepository blocRepository;

    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(Long idRes) {
        return reservationRepository.findById(idRes).get();
    }

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(idBloc);
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(cinEtudiant);

        if (!optionalChambre.isPresent() || !optionalEtudiant.isPresent()) {
            throw new RuntimeException("Chambre ou étudiant non trouvé.");
        }

        Chambre chambre = optionalChambre.get();
        Etudiant etudiant = optionalEtudiant.get();

        // Vérification de la capacité maximale de la chambre
        long reservationsCount = reservationRepository.countByChambreAndAnneeUniversitaireAndEstValideTrue(chambre, "2024-2025");
        if (reservationsCount >= chambre.getNumeroChambre()) {
            throw new RuntimeException("Capacité maximale atteinte pour cette chambre.");
        }

        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setChambre(chambre);
        reservation.setEtudiants((Set<Etudiant>) etudiant);  // Passer directement l'étudiant
        reservation.setEstValide(true);
        // Conversion de String à java.sql.Date
        String anneeUniversitaireStr = "2024-2025";
        Date anneeUniversitaire = Date.valueOf(anneeUniversitaireStr + "-01-01");  // Exemple de conversion en Date
        reservation.setAnneeUniversitaire(anneeUniversitaire);

        // Création du numéro de réservation
        String numReservation = chambre.getIdChambre() + "-" + "nomBloc" + "-" + "2024-2025";
        reservation.setIdReservation(Long.valueOf(numReservation));

        // Sauvegarde de la réservation
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // Rechercher la réservation associée au CIN de l'étudiant
        Reservation reservation = reservationRepository.findByEtudiantCin(cinEtudiant);
        if (reservation == null) {
            throw new IllegalArgumentException("Aucune réservation trouvée pour cet étudiant");
        }

        // Annuler la réservation
        reservation.setEstValide(false); // Mettre à jour l'état de la réservation
        reservation.setEtudiants(null); // Désaffecter l'étudiant

        // Désaffecter la chambre
        Chambre chambre = reservation.getChambre();
        if (chambre != null) {
            Bloc bloc = chambre.getBloc(); // Récupérer le bloc associé
            if (bloc != null) {
                bloc.setCapaciteBloc(bloc.getCapaciteBloc() + 1); // Incrémenter la capacité
                blocRepository.save(bloc); // Sauvegarder les modifications dans le bloc
            }
            chambre.getReservations().remove(reservation); // Retirer la réservation de la chambre
            chambreRepository.save(chambre); // Sauvegarder les modifications dans la chambre
        }

        reservationRepository.save(reservation); // Sauvegarder la réservation annulée
        return reservation;
    }

    // Méthode pour obtenir les réservations par année universitaire et nom d'université
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        return reservationRepository.findByAnneeUniversitaireAndChambreUniversiteNom(anneeUniversite, nomUniversite);
    }
}
