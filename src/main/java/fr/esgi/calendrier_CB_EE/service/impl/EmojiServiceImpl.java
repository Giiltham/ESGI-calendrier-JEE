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
    public void delete(Long id)
    {
        emojiRepository.deleteById(id);
    }

    @Override
    public void save(Emoji emoji)
    {
        emojiRepository.save(emoji);
    }

    @Override
    public Emoji findById(Long id)
    {
        return emojiRepository.findById(id).get();
    }

    @Override
    public List<Emoji> findAll()
    {
        return emojiRepository.findAll();
    }
}
