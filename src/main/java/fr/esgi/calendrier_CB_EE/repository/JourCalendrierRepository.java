package fr.esgi.calendrier_CB_EE.repository;


import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourCalendrierRepository extends JpaRepository<JourCalendrier,Long> {
}
