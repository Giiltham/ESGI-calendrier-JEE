package fr.esgi.calendrier_CB_EE.initialisation;


import fr.esgi.calendrier_CB_EE.business.*;
import fr.esgi.calendrier_CB_EE.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

  private final EmojiRepository emojiRepository;
  private final ThemeRepository themeRepository;
  private final JourCalendrierRepository jourCalendrierRepository;
  private final UtilisateurRepository utilisateurRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public void run(String... args) throws Exception {
    ajouterEmojis();
    ajouterThemes();
    ajouterJoursCalendrier();
    ajouterUtilisateur();
  }

  private void ajouterUtilisateur() {
    utilisateurRepository.save(new Utilisateur(1L, "Brunet", "Cédric","a@esgi.fr", passwordEncoder.encode("a"), themeRepository.findAll().get(0), 500));
  }

  private void ajouterEmojis() {
    emojiRepository.save(new Emoji(1L, "1f60a", "Smiley sourire"));
    emojiRepository.save(new Emoji(2L, "1f92f", "Smiley tête qui explose"));
    emojiRepository.save(new Emoji(3L, "1f9d0", "Smiley monocle"));
    emojiRepository.save(new Emoji(4L, "1f979", "Smiley larmes aux yeux"));
    emojiRepository.save(new Emoji(5L, "2764", "Coeur"));
  }

  private void ajouterThemes() {
    themeRepository.save(new Theme(1L, "Light"));
    themeRepository.save(new Theme(2L, "Dark"));
  }

  private void ajouterJoursCalendrier() {
    Calendar calendar = Calendar.getInstance();
    Month month = Month.of(calendar.get(Calendar.MONTH)+1);
    Year year = Year.of(calendar.get(Calendar.YEAR));
    boolean leapYear = Year.now().isLeap();

    for(long i = 0; i < month.length(leapYear); i++)
    {
      jourCalendrierRepository.save(
        new JourCalendrier(i+1, null, null, null, Math.toIntExact(i)+1, month.getValue(), year.getValue(), 20)
      );
    }
  }
}
