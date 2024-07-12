package fr.esgi.calendrier_CB_EE.repository;


import fr.esgi.calendrier_CB_EE.business.Gif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GifRepository extends JpaRepository<Gif,Long> {
}
