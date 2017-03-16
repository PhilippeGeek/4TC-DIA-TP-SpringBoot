package fr.insalyon.tc.dia.springapp.notes;

import fr.insalyon.tc.dia.springapp.asciidoc.AsciidocConverter;
import fr.insalyon.tc.dia.springapp.note.Note;
import fr.insalyon.tc.dia.springapp.note.NoteRepository;
import fr.insalyon.tc.dia.springapp.note.NotesController;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NotesController.class)
public class NotesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private AsciidocConverter asciidocConverter;

    @Test
    public void testFindOneNote() throws Exception {

        Note note = new Note("== Spring Boot");
        note.setId(1L);
        note.setRenderedContent("<h2>Spring Boot</h2>");

        given(this.noteRepository.findOne(1L)).willReturn(note);

        this.mockMvc
                .perform(get("/notes/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rawContent").value("== Spring Boot"));
    }

    @Test
    public void testCreateNote() throws Exception {

        Note note = new Note("== Spring Boot");
        note.setId(1L);
        note.setRenderedContent("<h2>Spring Boot</h2>");

        given(this.asciidocConverter.convertToHtmlSnippet("== Spring Boot")).willReturn("<h2>Spring Boot</h2>");
        given(this.noteRepository.save(any(Note.class))).willReturn(note);

        this.mockMvc
                .perform(post("/notes/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("text/asciidoc")
                        .content("== Spring Boot"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.renderedContent").value("<h2>Spring Boot</h2>"));

        verify(this.asciidocConverter, times(1)).convertToHtmlSnippet(eq("== Spring Boot"));
    }
}