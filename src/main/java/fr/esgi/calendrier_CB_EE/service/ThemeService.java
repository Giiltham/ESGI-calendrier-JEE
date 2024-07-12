package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Theme;
import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService
{
    List<Theme> findAll();

    Theme findById(Long id);

    void delete(Long id);

    void save(@Valid Theme theme);
}
