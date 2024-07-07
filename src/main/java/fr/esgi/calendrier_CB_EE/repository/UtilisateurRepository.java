package fr.esgi.calendrier_CB_EE.repository;


import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Utilisateur findByAdresseEmail(String username);
}
