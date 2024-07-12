package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Gif;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GifService {
    void delete(Long id);
    List<Gif> findAll();
    Gif findById(Long id);
    void save(Gif entity);
}
