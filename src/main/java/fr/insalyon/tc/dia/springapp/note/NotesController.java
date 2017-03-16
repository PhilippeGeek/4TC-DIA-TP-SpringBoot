package fr.insalyon.tc.dia.springapp.note;

import fr.insalyon.tc.dia.springapp.asciidoc.AsciidocConverter;
import fr.insalyon.tc.dia.springapp.asciidoc.AsciidoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NoteRepository repository;
    private final AsciidocConverter asciidoctorService;

    @Autowired
    public NotesController(NoteRepository repository, AsciidocConverter asciidoctorService){
        this.repository = repository;
        this.asciidoctorService = asciidoctorService;
    }

    @GetMapping("/{id}")
    public Note findNote(@PathVariable("id") Long id){
        return this.repository.findOne(id);
    }

}
