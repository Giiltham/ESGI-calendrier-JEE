package fr.esgi.calendrier_CB_EE.converter;

import fr.esgi.calendrier_CB_EE.business.Theme;
import fr.esgi.calendrier_CB_EE.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StringToThemeConverter implements Converter<String, Theme> {

    private ThemeRepository themeRepository;

    @Override
    public Theme convert(String source) {
        Long id = Long.valueOf(source);
        try {
            return themeRepository.findById(id).orElse(null);
        }catch (Exception e){
            return null;
        }
    }
}