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

import fr.esgi.calendrier_CB_EE.business.Gif;
import fr.esgi.calendrier_CB_EE.dto.GifDto;
import fr.esgi.calendrier_CB_EE.mapper.GifMapper;
import fr.esgi.calendrier_CB_EE.service.GifService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/gif")
@AllArgsConstructor
public class GifRestController
{
    @Autowired
    private final GifService gifService;

    @Autowired
    private final GifMapper gifMapper;

    @GetMapping("")
    @Operation(summary = "Get all gifs")
    public List<Gif> getAllGifs() {
        return gifService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get gif by id")
    public Gif getGifById(@PathVariable Long id) {
        return gifService.findById(id);
    }

    @PostMapping("")
    // @Operation(summary = "Create a new gif", responses = {@ApiResponse(responseCode = "201", description = "Ajout ok")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createGif(@Valid @RequestBody GifDto gifDto) {
        gifService.save(gifMapper.toEntity(gifDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete gif by id")
    public void deleteGif(@PathVariable(value = "id") Long id) {
        gifService.delete(id);
    }
}
