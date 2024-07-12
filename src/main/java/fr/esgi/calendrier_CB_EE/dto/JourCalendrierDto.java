package fr.esgi.calendrier_CB_EE.dto;

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.business.Reaction;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import jakarta.persistence.*;
import lombok.Value;

import java.util.List;

@Value
public class JourCalendrierDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Gif gif;

    @ManyToOne
    private Utilisateur utilisateur;

    private List<Reaction> reactions;

    private int jour;

    private int mois;

    private int annee;

    private int points;
}
