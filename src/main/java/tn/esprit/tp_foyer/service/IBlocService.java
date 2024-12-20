package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Bloc;

import java.util.List;

public interface IBlocService {

    public List<Bloc> retrieveAllBloc();
    public Bloc addBloc(Bloc b);
    public Bloc updateBloc(Bloc b);
    public Bloc retrieveBloc(Long idbloc);
    void removeBloc(Long idbloc);

    Bloc affecterChambresABloc(List<Long> numeroChambre, Long idBloc);

    void listeChambresParBloc ();

}
