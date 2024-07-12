package fr.esgi.calendrier_CB_EE.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.calendrier_CB_EE.business.Emoji;
import fr.esgi.calendrier_CB_EE.service.EmojiService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/emoji")
@AllArgsConstructor
public class EmojiRestController
{
    @Autowired
    private final EmojiService emojiService;

    @GetMapping("")
    @Operation(summary = "Get all emojis")
    public List<Emoji> getAllEmojis() {
        return emojiService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Emoji by id")
    public Emoji getEmojiById(@PathVariable Long id) {
        return emojiService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createEmoji(@Valid @RequestBody Emoji emoji) {
        emojiService.save(emoji);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Emoji by id")
    public void deleteEmoji(@PathVariable(value = "id") Long id) {
        emojiService.delete(id);
    }
}
