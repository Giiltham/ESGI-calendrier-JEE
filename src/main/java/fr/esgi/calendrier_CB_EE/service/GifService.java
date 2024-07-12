package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Gif;
import org.springframework.stereotype.Service;

@Service
public interface GifService {
    void ajouterGif(Gif gif);
    Gif recupererGif(Long id);
    void supprimerGif(Long id);
}
