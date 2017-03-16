package fr.insalyon.tc.dia.springapp.asciidoc;

public interface AsciidocConverter {

    /**
     * Convert the asciidoc markup given as an argument
     * to a complete HTML document (headers, style, etc).
     * @param asciidocSource asciidoc markup
     * @return a complete HTML document
     */
    String convertToHtmlDocument(String asciidocSource);

    /**
     * Convert the asciidoc markup given as an argument
     * to an HTML snippet, which means it does not
     * contain headers, style, footer, etc.
     * @param asciidocSource asciidoc markup
     * @return an HTML snippet
     */
    String convertToHtmlSnippet(String asciidocSource);
}
