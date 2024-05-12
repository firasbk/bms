package univie.pad.bms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univie.pad.bms.entities.Genre;
import univie.pad.bms.services.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("")
    public String getAllGenres(Model model) {
        model.addAttribute("templates/genres", genreService.getAllGenres());
        return "genres/list";
    }

    @GetMapping("/{id}")
    public String getGenreById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genre", genreService.getGenreById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id)));
        return "/genres/genre";
    }

    @PostMapping("/")
    public String createGenre(@ModelAttribute("genre") Genre genre) {
        genreService.createGenre(genre);
        return "redirect:/genres/";
    }

    @PutMapping("/{id}")
    public String updateGenre(@PathVariable("id") Long id, @ModelAttribute("genre") Genre genre) {
        genreService.updateGenre(id, genre);
        return "redirect:/genres/";
    }

    @DeleteMapping("/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres/";
    }
}