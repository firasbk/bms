package univie.pad.bms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univie.pad.bms.repositories.PublisherRepository;
import univie.pad.bms.entities.Publisher;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher updatedPublisher) {
        Optional<Publisher> existingPublisherOptional = publisherRepository.findById(id);
        if (existingPublisherOptional.isPresent()) {
            Publisher existingPublisher = existingPublisherOptional.get();
            existingPublisher.setName(updatedPublisher.getName());
            existingPublisher.setAddress(updatedPublisher.getAddress());
            return publisherRepository.save(existingPublisher);
        } else {
            throw new RuntimeException("Publisher not found with id: " + id);
        }
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
