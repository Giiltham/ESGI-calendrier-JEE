package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Reaction;
import fr.esgi.calendrier_CB_EE.repository.ReactionRepository;
import fr.esgi.calendrier_CB_EE.service.ReactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class ReactionServiceImpl implements ReactionService
{
    ReactionRepository reactionRepository;

    @Override
    public void save(Reaction reaction)
    {
        reactionRepository.save(reaction);
    }

    @Override
    public void delete(Long id)
    {
        reactionRepository.deleteById(id);
    }

    @Override
    public Reaction findById(Long id)
    {
        return reactionRepository.findById(id).get();
    }

    @Override
    public List<Reaction> findAll()
    {
        return reactionRepository.findAll();
    }
}
