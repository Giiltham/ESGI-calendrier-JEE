package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.repository.GifRepository;
import fr.esgi.calendrier_CB_EE.service.GifService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class GifServiceImpl implements GifService {

    private GifRepository gifRepository;

    @Override
    public void ajouterGif(Gif gif){
        gifRepository.save(gif);
    }
}
