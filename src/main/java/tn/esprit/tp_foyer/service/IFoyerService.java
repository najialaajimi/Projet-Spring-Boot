package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Foyer;

import java.util.List;

public interface IFoyerService {

    public List<Foyer> retrieveAllFoyer();
    public Foyer addFoyer(Foyer f);
    public Foyer updateFoyer(Foyer f);
    public Foyer retrieveFoyer(Long idFoyer);
    void removeFoyer(Long idFoyer);
    Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, Long idUniversite);
}
