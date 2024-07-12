package fr.esgi.calendrier_CB_EE.configuration;

import fr.esgi.calendrier_CB_EE.converter.StringToThemeConverter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private StringToThemeConverter stringToThemeConverter;

    @Override
    public void addFormatters(@SuppressWarnings("null") FormatterRegistry registry) {
        registry.addConverter(stringToThemeConverter);
    }
}