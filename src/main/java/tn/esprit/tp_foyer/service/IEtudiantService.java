package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> retrieveAllEtudiant();
    public List<Etudiant> addEtudiant(List<Etudiant> e);
    public Etudiant updateEtudiant(Etudiant e);
    public Etudiant retrieveEtudiant(Long IdE);
    void removeEtudiant(Long IdE);

}
