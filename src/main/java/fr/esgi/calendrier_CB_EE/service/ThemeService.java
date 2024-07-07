package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Theme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService {
    List<Theme> recupererThemes();
}
