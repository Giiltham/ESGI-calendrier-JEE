package fr.esgi.calendrier_CB_EE.controller;

import fr.esgi.calendrier_CB_EE.business.Emoji;
import fr.esgi.calendrier_CB_EE.business.JourCalendrier;
import fr.esgi.calendrier_CB_EE.business.Reaction;
import fr.esgi.calendrier_CB_EE.business.Utilisateur;
import fr.esgi.calendrier_CB_EE.service.JourCalendrierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

@Controller
@AllArgsConstructor
public class CalendrierController {
    private JourCalendrierService jourCalendrierService;

    @GetMapping({"index", "/", "calendrier"})
    public ModelAndView getCalendrierPage(@PageableDefault(size=7, sort={"jour"}) Pageable pageable
                                          ){

        ModelAndView mav = new ModelAndView("calendrier");

        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.addObject("utilisateur", utilisateur);

        Page<JourCalendrier> pageDeJourCalendrier = jourCalendrierService.recupererJours(pageable);

        mav.addObject("pageDeJourCalendrier", pageDeJourCalendrier);


        for(JourCalendrier jourCalendrier : pageDeJourCalendrier.getContent()){
            for(Reaction reaction : jourCalendrier.getReactions()){
                String emojiCharacter = String.format("&#x%s;", reaction.getEmoji().getCode());
                reaction.getEmoji().setCode(emojiCharacter);
            }
        }

        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        StringBuilder sortBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            sortBuilder.append(order.getProperty());
            sortBuilder.append(',');
            sortBuilder.append(order.getDirection());
            if (iterator.hasNext()) {
                sortBuilder.append("&sort=");
            }
        }
        mav.addObject("sort", sortBuilder.toString());
        return mav;
    }
}
