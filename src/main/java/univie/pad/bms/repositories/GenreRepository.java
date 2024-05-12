package univie.pad.bms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import univie.pad.bms.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
