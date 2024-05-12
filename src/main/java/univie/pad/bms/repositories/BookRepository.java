package univie.pad.bms.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import univie.pad.bms.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
