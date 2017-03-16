package fr.insalyon.tc.dia.springapp.note;

import fr.insalyon.tc.dia.springapp.asciidoc.AsciidocConverter;
import fr.insalyon.tc.dia.springapp.asciidoc.AsciidoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/", consumes = "text/asciidoc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> addNote(@RequestBody String asciidoc){
        Note n = new Note(asciidoc);
        n.setRenderedContent(asciidoctorService.convertToHtmlSnippet(asciidoc));
        return new ResponseEntity<>(repository.save(n), HttpStatus.CREATED);
    }

}
