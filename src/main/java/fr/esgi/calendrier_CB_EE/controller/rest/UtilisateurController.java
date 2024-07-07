package fr.esgi.calendrier_CB_EE.controller.rest;

import fr.esgi.calendrier_CB_EE.repository.UtilisateurRepository;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

}
