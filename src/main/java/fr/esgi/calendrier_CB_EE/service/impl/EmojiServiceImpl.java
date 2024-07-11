package fr.esgi.calendrier_CB_EE.service.impl;

import fr.esgi.calendrier_CB_EE.business.Emoji;
import fr.esgi.calendrier_CB_EE.repository.EmojiRepository;
import fr.esgi.calendrier_CB_EE.service.EmojiService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class EmojiServiceImpl implements EmojiService {

    EmojiRepository emojiRepository;

    @Override
    public List<Emoji> recupererEmojis() {
        return emojiRepository.findAll();
    }

    @Override
    public Emoji recupererEmoji(Long idEmoji) {
        return emojiRepository.findById(idEmoji).orElse(null);
    }
}
