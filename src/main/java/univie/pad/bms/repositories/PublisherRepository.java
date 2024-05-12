package univie.pad.bms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import univie.pad.bms.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
