package fr.insalyon.tc.dia.springapp.asciidoc;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.springframework.stereotype.Service;

@Service
public class AsciidoctorService implements AsciidocConverter {

    private final Asciidoctor asciidoctor;

    public AsciidoctorService(){
        // global setup
        this.asciidoctor = Asciidoctor.Factory.create();
    }

    @Override
    public String convertToHtmlDocument(String asciidocSource) {
        OptionsBuilder options = OptionsBuilder.options().safe(SafeMode.SAFE);
        return asciidoctor.convert(asciidocSource, options.headerFooter(true));
    }

    @Override
    public String convertToHtmlSnippet(String asciidocSource) {
        return asciidoctor.convert(asciidocSource, OptionsBuilder.options());
    }
}
