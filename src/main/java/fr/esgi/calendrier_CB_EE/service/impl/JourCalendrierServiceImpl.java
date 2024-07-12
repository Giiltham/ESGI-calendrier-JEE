package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.*;
import fr.esgi.calendrier_CB_EE.repository.JourCalendrierRepository;
import fr.esgi.calendrier_CB_EE.service.EmojiService;
import fr.esgi.calendrier_CB_EE.service.JourCalendrierService;
import fr.esgi.calendrier_CB_EE.service.ReactionService;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class JourCalendrierServiceImpl implements JourCalendrierService {

    private JourCalendrierRepository jourCalendrierRepository;
    private UtilisateurService utilisateurService;
    private EmojiService emojiService;
    private ReactionService reactionService;

    @Override
    public Page<JourCalendrier> recupererJours(Pageable pageable) {
        return jourCalendrierRepository.findAll(pageable);
    }

    @Override
    public JourCalendrier recupererJour(Long id) {
        return jourCalendrierRepository.findById(id).orElse(null);
    }

    @Override
    public void saveJour(JourCalendrier jourCalendrier) {
        jourCalendrierRepository.save(jourCalendrier);
    }

    @Override
    public void placerGif(Long idJourCalendrier, Gif gif) {
        JourCalendrier jour = this.recupererJour(idJourCalendrier);
        jour.setUtilisateur((Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        jour.setGif(gif);
        utilisateurService.retirerPoints(jour.getPoints());

    }

    @Override
    public void ajouterUneReaction(Long idJourCalendrier, Long idEmoji) {
        Emoji emoji = emojiService.findById(idEmoji);
        JourCalendrier jour = this.recupererJour(idJourCalendrier);
        Utilisateur utilisateur = ((Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Reaction reaction = new Reaction(null, utilisateur, emoji, jour);

        reactionService.ajouterReaction(reaction);

        List<Reaction> reactions = jour.getReactions();
        reactions.add(reaction);
        jour.setReactions(reactions);
        this.saveJour(jour);
    }
}
