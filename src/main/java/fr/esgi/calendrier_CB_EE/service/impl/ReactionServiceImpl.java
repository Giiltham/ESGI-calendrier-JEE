package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Reaction;
import fr.esgi.calendrier_CB_EE.repository.ReactionRepository;
import fr.esgi.calendrier_CB_EE.service.ReactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class ReactionServiceImpl implements ReactionService {
    ReactionRepository reactionRepository;

    @Override
    public void ajouterReaction(Reaction reaction) {
        reactionRepository.save(reaction);
    }
}
