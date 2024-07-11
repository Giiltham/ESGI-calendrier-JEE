package fr.esgi.calendrier_CB_EE.controller;

import fr.esgi.calendrier_CB_EE.business.Emoji;
import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.dto.GifDto;
import fr.esgi.calendrier_CB_EE.forms.TeleverserGifForm;
import fr.esgi.calendrier_CB_EE.mapper.GifMapper;
import fr.esgi.calendrier_CB_EE.service.EmojiService;
import fr.esgi.calendrier_CB_EE.service.GifService;
import fr.esgi.calendrier_CB_EE.service.JourCalendrierService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class GifController {
    private JourCalendrierService jourCalendrierService;
    private EmojiService emojiService;
    private GifService gifService;
    private GifMapper gifMapper;

    @GetMapping({"placer-gif-distant"})
    public ModelAndView getPlacerGifDistantPage(@RequestParam(value = "id") Long id){
        ModelAndView mav = new ModelAndView("placerGifDistant");

        JourCalendrier jourCalendrier = jourCalendrierService.recupererJour(id);
        mav.addObject("jourCalendrier", jourCalendrier);

        return mav;
    }

    @GetMapping({"televerser-gif"})
    public ModelAndView getTeleverserGifPage(@RequestParam(value = "id") Long id){
        ModelAndView mav = new ModelAndView("televerserGif");

        JourCalendrier jourCalendrier = jourCalendrierService.recupererJour(id);
        TeleverserGifForm televerserGifForm = new TeleverserGifForm();
        televerserGifForm.setJourCalendrierId(id);
        mav.addObject("jourCalendrier", jourCalendrier);
        mav.addObject("televerserGifForm", televerserGifForm);
        mav.addObject("erreurs", new ArrayList<>());
        return mav;
    }

    @PostMapping({"televerser-gif"})
    public ModelAndView postTeleverserGifPage(@ModelAttribute TeleverserGifForm televerserGifForm){
        ModelAndView mav = new ModelAndView();

        // Le fichier téléversé doit s'écrire dans le dossier src/main/resources/static
        Path chemin = Paths.get("src/main/resources/static/");
        Path cheminFichier;
        try (InputStream inputStream = televerserGifForm.getFile().getInputStream()) {
            cheminFichier = File.createTempFile("upload", ".gif", chemin.toFile()).toPath();
            Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            mav = getTeleverserGifPage(televerserGifForm.getJourCalendrierId());
            mav.setViewName("televerserGif");
            return mav;
        }

        Gif gif = gifMapper.toEntity(new GifDto(cheminFichier.getFileName().toString(),televerserGifForm.getLegende()));
        gifService.ajouterGif(gif);
        jourCalendrierService.placerGif(televerserGifForm.getJourCalendrierId(),gif);
        mav.setViewName("redirect:index");
        return mav;

    }


    @PostMapping({"placer-gif-distant"})
    public ModelAndView postPlacerGifDistantPage(@ModelAttribute @Valid GifDto gifDto, BindingResult bindingResult, @RequestParam(value = "id") Long id){
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav = this.getPlacerGifDistantPage(id);
            mav.addObject("erreurs", bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList()));
            return mav;
        }

        Gif gif = gifMapper.toEntity(gifDto);
        gifService.ajouterGif(gif);
        jourCalendrierService.placerGif(id,gif);
        mav.setViewName("redirect:index");

        return mav;
    }

    @GetMapping({"reagir-gif"})
    public ModelAndView getReagirGifPage(@RequestParam(value = "id") Long id){
        ModelAndView mav = new ModelAndView("reagirGif");

        JourCalendrier jourCalendrier = jourCalendrierService.recupererJour(id);
        mav.addObject("jourCalendrier", jourCalendrier);

        List<Emoji> emojis =  emojiService.recupererEmojis();
        emojis.forEach(emoji -> {
            String emojiCharacter = String.format("&#x%s;", emoji.getCode());
            emoji.setCode(emojiCharacter);
        });
        mav.addObject("emojis",emojis);

        return mav;
    }

    @PostMapping({"reagir-gif"})
    public ModelAndView postReagirGifPage(@RequestParam(value = "emoji") Long idEmoji, @RequestParam(value = "id") Long idJourCalendrier){
        ModelAndView mav = new ModelAndView();

        jourCalendrierService.ajouterUneReaction(idJourCalendrier, idEmoji);
        mav.setViewName("redirect:index");

        return mav;
    }

}
