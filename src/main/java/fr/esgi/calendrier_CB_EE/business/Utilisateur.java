package fr.esgi.calendrier_CB_EE.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@esgi\\.fr$", message = "L'email doit appartenir au domaine")
  private String adresseEmail;

  @Length(min = 3, message = "Le mot de passe doit contenir au moins 3 caractères")
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
