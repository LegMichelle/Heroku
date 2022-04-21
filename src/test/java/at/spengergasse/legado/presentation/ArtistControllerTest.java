package at.spengergasse.legado.presentation;

import at.spengergasse.legado.presentation.restapi.ArtistDto;
import at.spengergasse.legado.services.ArtistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArtistService artistService;


    @Test
    void verifyAllArtists() throws Exception
    {
        var artistsList= List.of(ArtistDto.builder()
                .stageName("Lisa")
                .birthName("Lalisa")
                .birthDate(LocalDate.parse("1990-01-08"))
                .build()
        );

        when(artistService.allArtists()).thenReturn(artistsList);

        var expectedResult = objectMapper.writeValueAsString(artistsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/artists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void verifyInsert() throws Exception
    {
        var artistDto = ArtistDto.builder()
                        .stageName("Lisa")
                        .birthName("Lalisa")
                        .birthDate(LocalDate.parse("1990-01-08"))
                        .build();
        var artistAsJson = objectMapper.writeValueAsString(artistDto);

        when(artistService.insert(any())).thenReturn(artistDto);
        mockMvc.perform(post("/artists").content(artistAsJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(artistAsJson));
    }

    @Test
    void verifyFullInsertReadCycle() throws Exception
    {
        var artistAsJson = objectMapper.writeValueAsString(
                ArtistDto.builder()
                        .stageName("Lisa")
                        .birthName("Lalisa")
                        .birthDate(LocalDate.parse("1990-01-08"))
                        .build()
        );

        mockMvc.perform(post("/artists").content(artistAsJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/artists"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
