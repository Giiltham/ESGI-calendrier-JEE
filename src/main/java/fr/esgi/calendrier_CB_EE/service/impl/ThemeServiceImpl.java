package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Theme;
import fr.esgi.calendrier_CB_EE.repository.ThemeRepository;
import fr.esgi.calendrier_CB_EE.service.ThemeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private ThemeRepository themeRepository;


    @Override
    public List<Theme> recupererThemes() {
        return themeRepository.findAll();
    }
}
