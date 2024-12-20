package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.repository.FoyerRepository;
import tn.esprit.tp_foyer.repository.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
/*Ken ma tektebech @AllArgsConstructor sinon thot @AutoWired fo9
@Autowired
private UniversiteRepository universiteRepository;
najem n5alaha private wala default
*/
public class UniversiteServiceImpl implements IUniversiteService {

    UniversiteRepository universiteRepository;

    FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversit() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(Long idUniversite) {
        return universiteRepository.findById(idUniversite).get();
    }

    @Override
    public Universite affectFoyerAUniversite(Long idFoyer, String nomUniversite) {
        //Récupérer le foyer par son Id
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        //orElse null ma ytala3li chy
        //sinon nbadel bel ligne .orElseThrow (fonction flechée) bech n'afficher une message d'erreur
        // .orElseThrow(() -> new RuntimeException("Foyer introuvable avec l'ID : " + idFoyer));

        //Récupérer l'Université par son Nom
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite).orElse(null);
        // .orElseThrow(() -> new RuntimeException("Universite introuvable avec le nom : " + nomUniversite));

        //Vérifier si le foyer ou l'université est déja associé(e)
        if (foyer.getUniversite() != null || universite.getFoyer() != null)
            throw new RuntimeException("L'association existe déjà pour ce foyer ou cette université");

        //Associer le foyer à l'université
        universite.setFoyer(foyer);
        //foyer.setUniversite(universite);
        /*nhote ken ki yabda clé etrangere fi deux table khater cas mta3na
         feha oneToOne donc idFoyer mawjoud ken fi université*/

        //Sauvgarder les changements
        universiteRepository.save(universite);
        //foyerRepository.save(foyer);
        /*nhote ken ki yabda clé etrangere fi deux table khater cas mta3na
        feha oneToOne donc idFoyer mawjoud ken fi université*/
        return universite;
    }

    @Override
    public Universite desaffectFoyerUniversite(Long idUniversite) {
        //Récupérer l'université By Id
        Universite universite= universiteRepository.findById(idUniversite).orElse(null);
        //.orElseThrow(() -> new RuntimeException("Université introuvable avec l'ID : " + idUniversite))

        //vérifier si l'université est liée a un foyer
        if (universite.getFoyer() == null)
            throw new RuntimeException("Aucune foyer n'est actuellement");

        //Désaffecter le foyer de l'université
        Foyer foyer = universite.getFoyer();
        universite.setFoyer(null);
        //foyer.setUniversite(null);
        /*meme aussi kime fi affecteFoyerAUniversite*/

        //Sauvgarder les modifications
        universiteRepository.save(universite);
        //foyerRepository.save(foyer);
        /*meme aussi kime fi affecteFoyerAUniversite*/
        return universite;
    }

}
