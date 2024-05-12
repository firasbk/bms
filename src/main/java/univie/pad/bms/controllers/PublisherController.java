package univie.pad.bms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univie.pad.bms.entities.Book;
import univie.pad.bms.entities.Publisher;
import univie.pad.bms.services.PublisherService;

import java.util.Optional;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("")
    public String getAllPublishers(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        System.out.println("publishers" + publisherService.getAllPublishers().toString());
        return "publishers/list";
    }

    @GetMapping("/{id}")
    public String getPublisherById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("publisher", publisherService.getPublisherById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id)));
        return "publishers/publisher";
    }

    @PostMapping("/")
    public String createPublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.createPublisher(publisher);
        return "redirect:/publishers/";
    }

    @PutMapping("/{id}")
    public String updatePublisher(@PathVariable("id") Long id, @ModelAttribute("publisher") Publisher publisher) {
        publisherService.updatePublisher(id, publisher);
        return "redirect:/publishers/";
    }

    @DeleteMapping("/{id}")
    public String deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishers/";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model) throws Exception {
        Optional<Publisher> ePublisher = publisherService.getPublisherById(id);
        if (ePublisher.isPresent()) {
            model.addAttribute("publisher", ePublisher.get());
            return "publishers/publisher-form";
        }
        throw new Exception("No such publisher id");
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/new-publisher";
    }
}
