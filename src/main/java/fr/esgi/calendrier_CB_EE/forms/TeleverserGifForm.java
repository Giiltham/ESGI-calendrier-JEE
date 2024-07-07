package fr.esgi.calendrier_CB_EE.forms;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class TeleverserGifForm {
    private Long jourCalendrierId = null;
    private MultipartFile file = null;
    private String legende = null;
}
