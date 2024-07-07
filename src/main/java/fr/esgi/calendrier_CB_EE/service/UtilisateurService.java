package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UtilisateurService extends UserDetailsService {
    Utilisateur recupererUtilisateur(String username);

    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);

    void retirerPoints(int points);
}
