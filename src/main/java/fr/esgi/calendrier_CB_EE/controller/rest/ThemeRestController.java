package fr.esgi.calendrier_CB_EE.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.calendrier_CB_EE.business.Theme;
import fr.esgi.calendrier_CB_EE.service.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/theme")
@AllArgsConstructor
public class ThemeRestController
{
    @Autowired
    private final ThemeService themeService;

    @GetMapping("")
    @Operation(summary = "Get all themes")
    public List<Theme> getAllThemes() {
        return themeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Theme by id")
    public Theme getThemeById(@PathVariable Long id) {
        return themeService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createTheme(@Valid @RequestBody Theme theme) {
        themeService.save(theme);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Theme by id")
    public void deleteTheme(@PathVariable(value = "id") Long id) {
        themeService.delete(id);
    }
}
