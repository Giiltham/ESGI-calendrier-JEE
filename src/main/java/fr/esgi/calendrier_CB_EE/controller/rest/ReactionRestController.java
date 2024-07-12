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

import fr.esgi.calendrier_CB_EE.business.Reaction;
import fr.esgi.calendrier_CB_EE.service.ReactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/reaction")
@AllArgsConstructor
public class ReactionRestController
{
    @Autowired
    private final ReactionService reactionService;

    @GetMapping("")
    @Operation(summary = "Get all reactions")
    public List<Reaction> getAllReactions() {
        return reactionService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Reaction by id")
    public Reaction getReactionById(@PathVariable Long id) {
        return reactionService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createReaction(@Valid @RequestBody Reaction reaction) {
        reactionService.save(reaction);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Reaction by id")
    public void deleteReaction(@PathVariable(value = "id") Long id) {
        reactionService.delete(id);
    }
}
