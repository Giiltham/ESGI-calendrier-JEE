package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.repository.GifRepository;
import fr.esgi.calendrier_CB_EE.service.GifService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class GifServiceImpl implements GifService
{
    private GifRepository gifRepository;

    @Override
    public void save(Gif gif)
    {
        gifRepository.save(gif);
    }

    @Override
    public Gif findById(Long id)
    {
        return gifRepository.findById(id).get();
    }

    @Override
    public void delete(Long id)
    {
        gifRepository.deleteById(id);
    }

    @Override
    public List<Gif> findAll()
    {
        return gifRepository.findAll();
    }
}
