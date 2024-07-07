package fr.esgi.calendrier_CB_EE.controller;

import fr.esgi.calendrier_CB_EE.business.Theme;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.service.ThemeService;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private UtilisateurService utilisateurService;
    private ThemeService themeService;

    @GetMapping(value = "/connexion")
    public String loginPage(){
        return "connexion";
    }

    @GetMapping(value = "/inscription")
    public String registerPage(Model model){
        model.addAttribute("utilisateur", new Utilisateur() {});
        List<Theme> themes = themeService.recupererThemes();
        model.addAttribute("themes", themes);
        return "inscription";
    }

    @PostMapping(value = "/inscription")
    public String register(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.ajouterUtilisateur(utilisateur);
        return "redirect:/connexion";
    }
}
