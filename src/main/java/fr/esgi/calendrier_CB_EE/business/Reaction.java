package fr.esgi.calendrier_CB_EE.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;
    @ManyToOne
    private Emoji emoji;

    @ManyToOne
    private JourCalendrier jourCalendrier;
}
