package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.repository.EtudiantRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class EtudiantServiceImpl implements IEtudiantService {

    EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiant() {
        return etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> addEtudiant(List<Etudiant> e) {
        return (List<Etudiant>) etudiantRepository.saveAll(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long IdE) {
        return etudiantRepository.findById(IdE).get();
    }

    @Override
    public void removeEtudiant(Long IdE) {
        etudiantRepository.deleteById(IdE);
    }
}
