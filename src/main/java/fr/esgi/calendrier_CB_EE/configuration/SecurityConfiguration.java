package fr.esgi.calendrier_CB_EE.configuration;

import fr.esgi.calendrier_CB_EE.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration
{
	private final UtilisateurService service;
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// On peut se connecter sans utiliser https
	http.csrf(csrf -> csrf.disable())
	.formLogin(login -> login
				.usernameParameter("adresseEmail")
				.passwordParameter("motDePasse")
				.loginPage("/connexion")
				.defaultSuccessUrl("/")
				.permitAll()
	).userDetailsService(service)

	.authorizeHttpRequests(requests ->
	requests.requestMatchers("/swagger-ui/**").permitAll()
	.requestMatchers("/connexion", "/inscription").permitAll()
	.requestMatchers("/api/**").authenticated()
	.requestMatchers( "/static/**","/public/**").permitAll()
	.requestMatchers("/", "index", "calendrier").authenticated()
	.requestMatchers("placer-gif-distant", "televerser-gif", "reagir-gif").authenticated()
	)

	.headers(header -> header.frameOptions(FrameOptionsConfig::disable));
		return http.build();
	}

}