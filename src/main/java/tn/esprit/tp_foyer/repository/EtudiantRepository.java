package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Etudiant;

import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Optional<Etudiant> findByCinEt(Long cinEt);
/*
    @Override
    List<Etudiant> findAll();

    List<Etudiant> findByDateNaissanceBetween(Date dateD, Date dateF);
*/
}

