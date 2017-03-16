package fr.insalyon.tc.dia.springapp.note;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("serial")
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rawContent;

    @Column(nullable = false)
    private String renderedContent;

    @SuppressWarnings("unused")
    private Note() {
    }

    public Note(String rawContent) {
        this.rawContent = rawContent;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRawContent() {
        return this.rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public String getRenderedContent() {
        return this.renderedContent;
    }

    public void setRenderedContent(String renderedContent) {
        this.renderedContent = renderedContent;
    }

}
