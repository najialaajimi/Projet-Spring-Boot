package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.sql.Date;
import java.util.List;

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


}

