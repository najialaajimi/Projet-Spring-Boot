package tn.esprit.tp_foyer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBolc;

    private String nomBloc;

    private Long capaciteBloc;

    @ManyToOne
    private Foyer foyer;


    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chambre> chambres;


}
