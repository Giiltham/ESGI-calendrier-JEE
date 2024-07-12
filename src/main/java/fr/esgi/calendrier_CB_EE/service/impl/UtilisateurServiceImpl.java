package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.repository.UtilisateurRepository;
import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public Utilisateur recupererUtilisateur(String username) {
		return utilisateurRepository.findByAdresseEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("Le nom d'utilisateur ne peut pas être vide");
		}

		Utilisateur utilisateur = utilisateurRepository.findByAdresseEmail(username);

		if (utilisateur == null) {
			throw new UsernameNotFoundException("Utilisateur " + username + " introuvable");
		}

		return utilisateur;
	}

    // private List<GrantedAuthority> getGrantedAuthorities(Utilisateur utilisateur)
	// {
    //     List<GrantedAuthority> authorities = new ArrayList<>();
	// 	return authorities;
    // }

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		utilisateur.setPoints(500);

		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public void retirerPoints(int points) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		utilisateur.setPoints(Math.max(0, utilisateur.getPoints() - points));
		this.utilisateurRepository.save(utilisateur);
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur)
	{
		utilisateurRepository.delete(utilisateur);
	}

}