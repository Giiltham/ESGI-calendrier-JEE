package fr.esgi.calendrier_CB_EE.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class GifDto {
    @Pattern(regexp = "^http(s|)://.*\\.(?i)gif$", message = "Votre URL doit se terminer par .gif")
    private String url;
    private String legende;

}
