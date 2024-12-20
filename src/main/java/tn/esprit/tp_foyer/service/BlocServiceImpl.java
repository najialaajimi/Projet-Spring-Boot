package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor

public class BlocServiceImpl implements IBlocService {

    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBloc() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(Long idbloc) {
        blocRepository.deleteById(idbloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numeroChambre, Long idBloc) {
        //Récupérer le bloc par ID
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable avec l'ID : " + idBloc));
                //.orElse(null);

        //Récuperer la liste de chambre (Récupérer lles chambres par leurs numeros
        List<Chambre> listchambre= chambreRepository.findAllByNumeroChambreIn(numeroChambre);
        //on comparer par la taille de list : size()
        if (listchambre.size() != numeroChambre.size()){
            throw new RuntimeException("Un ou plusieurs chambres sont introuvable");
        }
        //Vérifier si certains chambres sont déja affectés a un autre bloc
        for (Chambre chambre : listchambre){
            if (chambre.getBloc() != null && chambre.getBloc().getIdBolc() != idBloc){
                throw new RuntimeException("La chambre "+ chambre.getNumeroChambre() +
                        " est déjà affectée à un autre Bloc");
            }
        }
        //Associer les chambres au Bloc

        for (Chambre chambre: listchambre){
            chambre.setBloc(bloc);
        }
        //Ajouter les Chambres du Bloc

        //bloc.getChambres().addAll(listchambre);
        /*najem nesta8na 3laha puisque li ani 3malet setBloc fi boucle for "Associer les chambres au bloc*/

        //Sauvgarder les changements

        //blocRepository.save(bloc);
        chambreRepository.saveAll(listchambre);

        return bloc;
    }


    @Scheduled(cron = "*/59 * * * * *")
    public void listeChambresParBloc() {
        //Récuperer la liste de bloc
        List<Bloc> allblocs = blocRepository.findAll();
        for (Bloc bloc : allblocs){
            log.info("*******************************");
            log.info("Bloc => "+ bloc.getNomBloc() +
                    "ayant une capacité" + bloc.getCapaciteBloc());
            if (bloc.getChambres() == null || bloc.getChambres().isEmpty()){
                log.info("Pas de chambre disponible dans ce bloc");
            }else {
                log.info("La Liste des chambres pour ce bloc: ");
                /*
                    List<Chambre> chambresListe = bloc.getChambres();
                    puis on remplace bloc.getChambres() par chambresListe
                    donc
                    List<Chambre> chambresListe = bloc.getChambres();
                    for(chambre ch: chambresList){
                        log.info("Num Chambre: " + chambre.getNumeroChambre()
                            + "type: " + chambre.getTypeC());
                            }
                */
                bloc.getChambres().forEach(chambre -> {
                    log.info("Num Chambre: " + chambre.getNumeroChambre()
                            + "type: " + chambre.getTypeC());
                });
            }
        }
        //Récupérer la liste De Chambre
        List<Chambre> allchambre = chambreRepository.findAll();


    }
}
