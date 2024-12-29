package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    @Autowired
    ChambreRepository chambreRepository;

    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC) {
        //methode JPQL
        return chambreRepository.findChambresByBlocAndType(idBloc,typeC);
        //methode Keyword
        //return chambreRepository.findAllByBlocIdBlocAndTypeC(idBloc,typeC);
    }
    // Service pour obtenir les chambres par le nom de l'université
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findByUniversiteNom(nomUniversite);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.findByFoyerUniversiteNomAndTypeChambreAndReservationsAnneeUniversitaireNotAndReservationsEstValide(
                nomUniversite, type);
    }

    @Scheduled(cron = "0 * * * * * ")
    public void pourcentageChambreParTpe(){
        List<Chambre> chambres = chambreRepository.findAll();//Recupérer les données
        //Nombre total de chambre
        int totalChambres = chambres.size();
        log.info("Nombre total des chambres: " + totalChambres);
        if (totalChambres > 0){
            //Initialisation d'une map pour compter les chambres par types
            Map<String, Integer> countByType = new HashMap<>();//Parcourir la liste des chambres
            for (Chambre chambre : chambres){
                String type = String.valueOf(chambre.getTypeC());
                countByType.put(type, countByType.getOrDefault(type, 0)+1);
                //put(cle, valeur);
            }
            for (Map.Entry<String, Integer> entry : countByType.entrySet()){
                String type = entry.getKey();
                int count = entry.getValue();
                double pourcentage = (count * 100.0)/totalChambres;
                log.info("Le pourcentage des chambres pour le type"+ type + "est egale à"+pourcentage);
            }
        }else {
            log.info("Aucune Chambre disponible pour le calcul des pourcentage");
        }
    }

    @Scheduled(cron = "0 */5 * * * *") // Se déclenche toutes les 5 minutes
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        // Récupérer toutes les chambres depuis la base de données
        List<Chambre> chambres = chambreRepository.findAll();

        for (Chambre chambre : chambres) {
            int capaciteMaximale = getCapaciteMaximale(chambre.getTypeC());
            int placesOccupées = chambre.getReservations().size();
            int placesDisponibles = capaciteMaximale - placesOccupées;

            if (placesDisponibles <= 0) {
                log.info("La chambre {} {} est complète", chambre.getTypeC(), chambre.getNumeroChambre());
            } else {
                log.info("Le nombre de place disponible pour la chambre {} {} est {}",
                        chambre.getTypeC(), chambre.getNumeroChambre(), placesDisponibles);
            }
        }
    }

    // Exemple de méthode pour obtenir la capacité maximale en fonction du type de chambre
    private int getCapaciteMaximale(TypeChambre typeC) {
        switch (typeC) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 4;
            default:
                return 0;
        }
    }
}

