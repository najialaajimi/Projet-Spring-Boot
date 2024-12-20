package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Universite;

import java.util.List;

public interface IUniversiteService {

    public List<Universite> retrieveAllUniversit();
    public Universite addUniversite(Universite u);
    public Universite updateUniversite(Universite u);
    public Universite retrieveUniversite(Long idUniversite);
    Universite affectFoyerAUniversite(Long idFoyer, String nomUniversite);
    Universite desaffectFoyerUniversite(Long idUniversite);
}
