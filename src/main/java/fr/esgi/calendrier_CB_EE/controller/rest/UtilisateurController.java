package fr.esgi.calendrier_CB_EE.controller.rest;

import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController
{
    private final UtilisateurService utilisateurService;

    @GetMapping("/{username}")
    public ResponseEntity<Utilisateur> get(@PathVariable final String username)
    {
        return ResponseEntity.ok(utilisateurService.recupererUtilisateur(username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable final String username)
    {
        Utilisateur utilisateur = utilisateurService.recupererUtilisateur(username);
        utilisateurService.supprimerUtilisateur(utilisateur);
        return ResponseEntity.ok("Utilisateur supprimé");
    }

    @PostMapping
    @Operation(description = "Méthode qui permet d'ajouter un utilisateur", responses = {@ApiResponse(responseCode="201", description ="Ajout ok")})
    public ResponseEntity<Utilisateur> save(@RequestBody final Utilisateur utilisateur)
    {
        return ResponseEntity.ok(utilisateurService.ajouterUtilisateur(utilisateur));
    }
}
