package fr.esgi.calendrier_CB_EE.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JourCalendrier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Gif gif;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "jourCalendrier")
    private List<Reaction> reactions;

    private int jour;

    private int mois;

    private int annee;

    private int points;
}
