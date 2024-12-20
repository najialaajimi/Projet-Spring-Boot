package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    List<Chambre> findAllByNumeroChambreIn(List<Long> numeroChambre);

    //1ére Méthode JPQL
    @Query("SELECT ch  FROM Chambre ch"+
            "WHERE ch.typeC =: typeC"+
            "AND ch.bloc.idBloc =: idBloc")
    //ch.bloc.idBloc jbedet bloc khater nahki 3ala Id kima na3emlou fi ey table fih Id lazem nejbed esem tableau mta3

    List<Chambre> findChambresByBlocAndType (
            @Param("idBloc") Long idBloc,
            @Param("typeC") TypeChambre typeC);

    //2éme Methode Keywords
    List<Chambre> findAllByBlocIdBlocAndTypeC(Long idBloc, TypeChambre typeC);
    //BlocIdBloc(nahki table Bloc de IdBloc)

    Optional<Chambre> findById(Long id);
    Long countByChambreIdAndEstValideTrue(Long chambreId);

    // Rechercher les chambres par le nom de l'université
    List<Chambre> findByUniversiteNom(String nomUniversite);

    // Rechercher les chambres qui ne sont pas réservées pour l'année universitaire et par type
    List<Chambre> findByFoyerUniversiteNomAndTypeChambreAndReservationsAnneeUniversitaireNotAndReservationsEstValide(
            String nomUniversite,
            TypeChambre type);
}
