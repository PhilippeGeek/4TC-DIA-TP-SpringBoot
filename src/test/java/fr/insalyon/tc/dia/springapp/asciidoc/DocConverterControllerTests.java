package fr.insalyon.tc.dia.springapp.asciidoc;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.never;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DocConverterController.class)
public class DocConverterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsciidoctorService asciidoctorService;

    @Test
    public void shouldConvertAsciidocToHtml() throws Exception {

        given(this.asciidoctorService.convertToHtmlDocument("== test content"))
                .willReturn("<h2>Test Content</h2>");

        this.mockMvc
                .perform(post("/asciidoc")
                        .content("== test content")
                        .contentType("text/asciidoc")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("<h2>Test Content</h2>"));
    }

    @Test
    public void shouldNotAcceptPlainText() throws Exception {

        this.mockMvc
                .perform(post("/asciidoc")
                        .content("== test content")
                        .contentType("text/plain")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isUnsupportedMediaType());
        verify(this.asciidoctorService, never()).convertToHtmlDocument(any());
    }
}
