package univie.pad.bms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univie.pad.bms.repositories.GenreRepository;
import univie.pad.bms.entities.Genre;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, Genre updatedGenre) {
        Optional<Genre> existingGenreOptional = genreRepository.findById(id);
        if (existingGenreOptional.isPresent()) {
            Genre existingGenre = existingGenreOptional.get();
            existingGenre.setName(updatedGenre.getName());
            return genreRepository.save(existingGenre);
        } else {
            throw new RuntimeException("Genre not found with id: " + id);
        }
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
