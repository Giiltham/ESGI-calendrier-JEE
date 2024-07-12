package fr.esgi.calendrier_CB_EE.service;

import java.util.List;

import fr.esgi.calendrier_CB_EE.business.Reaction;
import jakarta.validation.Valid;

public interface ReactionService {

    List<Reaction> findAll();

    Reaction findById(Long id);

    void save(@Valid Reaction reaction);

    void delete(Long id);
}
