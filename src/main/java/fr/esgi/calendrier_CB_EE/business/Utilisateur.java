package fr.esgi.calendrier_CB_EE.business;

import fr.esgi.calendrier_CB_EE.business.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Utilisateur implements UserDetails {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String nom;

  private String prenom;

  @Column(unique = true)
  private String adresseEmail;

  private String motDePasse;

  @ManyToOne(optional = false)
  private Theme theme;

  private Integer points;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.getMotDePasse();
  }

  @Override
  public String getUsername() {
    return this.getAdresseEmail();
  }
}
