package fr.insalyon.tc.dia.springapp.asciidoc;

import fr.insalyon.tc.dia.springapp.asciidoc.AsciidoctorService;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocConverterController {

    private final AsciidoctorService converter;

    @Autowired
    public DocConverterController(AsciidoctorService converter) {
        this.converter = converter;
    }

    @PostMapping(path="/asciidoc", produces = MediaType.TEXT_HTML_VALUE, consumes = "text/asciidoc")
    @ResponseBody
    public String convert(@RequestBody String asciidocSource){
        return converter.convertToHtmlDocument(asciidocSource);
    }

}
