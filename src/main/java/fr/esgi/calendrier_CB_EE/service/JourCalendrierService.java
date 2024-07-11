package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface JourCalendrierService {
    Page<JourCalendrier> recupererJours(Pageable pageable);
    JourCalendrier recupererJour(Long id);

    void saveJour(JourCalendrier jourCalendrier);

    void placerGif(Long idJourCalendrier, Gif gif);

    void ajouterUneReaction(Long idJourCalendrier, Long idEmoji);
}
