package fr.esgi.calendrier_CB_EE.controller;

import fr.esgi.calendrier_CB_EE.business.Theme;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.service.ThemeService;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AuthController {

    private UtilisateurService utilisateurService;
    private ThemeService themeService;

    @GetMapping(value = "/connexion")
    public String loginPage()
    {
        return "connexion";
    }

    @GetMapping(value = "/inscription")
    public String registerPage(Model model)
    {
        model.addAttribute("utilisateur", new Utilisateur() {});
        List<Theme> themes = themeService.recupererThemes();
        model.addAttribute("themes", themes);

        return "inscription";
    }

    @PostMapping(value = "/inscription")
    public ModelAndView register(@ModelAttribute @Valid Utilisateur utilisateur, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.addObject("erreurs", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
            List<Theme> themes = themeService.recupererThemes();
            mav.addObject("themes", themes);
            mav.setViewName("inscription");
            return mav;
        }

        utilisateurService.ajouterUtilisateur(utilisateur);

        mav.setViewName("redirect:/connexion");
        return mav;
    }
}
