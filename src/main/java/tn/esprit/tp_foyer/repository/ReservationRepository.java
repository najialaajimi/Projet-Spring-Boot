package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.entity.Reservation;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Reservation findByEtudiantCin(Long cin);
    // Trouver une réservation par étudiant
    Reservation findByEtudiant(Etudiant etudiant);

    // Méthode pour compter les réservations valides par chambre et année universitaire
    Long countByChambreAndAnneeUniversitaireAndEstValideTrue(Chambre chambre, String anneeUniversitaire);
    // Rechercher les réservations par année universitaire et par nom d'université
    List<Reservation> findByAnneeUniversitaireAndChambreUniversiteNom(Date anneeUniversitaire, String nomUniversite);

}
