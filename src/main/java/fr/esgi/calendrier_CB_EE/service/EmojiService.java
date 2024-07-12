package fr.esgi.calendrier_CB_EE.service;

import fr.esgi.calendrier_CB_EE.business.Emoji;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmojiService {

    List<Emoji> recupererEmojis();

    Emoji recupererEmoji(Long idEmoji);
}
