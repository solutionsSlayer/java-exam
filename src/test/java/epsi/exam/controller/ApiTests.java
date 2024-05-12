package epsi.exam.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = epsi.exam.ExamApplication.class)
@AutoConfigureMockMvc
public class ApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateClient() throws Exception {
        mockMvc.perform(post("/client/create")
                .param("nom", "Dupont")
                .param("prenom", "Jean")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"));
    }

    @Test
    void testCreateAndGetSapin() throws Exception {
        String response = mockMvc.perform(post("/sapin/create")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Long sapinId = 1L;

        mockMvc.perform(get("/sapin/get")
                .param("id", sapinId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testVenteSapin() throws Exception {
        mockMvc.perform(post("/client/create")
                .param("nom", "Test")
                .param("prenom", "Client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

                mockMvc.perform(post("/sapin/create")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/sapin/vente")
                .param("idSapin", "1")
                .param("idClient", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
} 