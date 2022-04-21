package at.spengergasse.legado.presentation;

import at.spengergasse.legado.presentation.restapi.HelloWorld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ensureGetHelloRequestWithPathValue() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/abc"))
                .andDo(print())
                //.andExpect(status().is(400))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Hello World: abc"));
    }

    @Test
    void HelloRequestWithoutIdentifier() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/"))
                .andDo(print())
                //.andExpect(status().is(400))
                .andExpect(status().is(404));
    }

    @Test
    void exploreRecord()
    {
        var helloWorld_1 = new HelloWorld("xxx");
        var helloWorld_2 = new HelloWorld("xxx");
        assertThat(helloWorld_1.equals(helloWorld_2)).isTrue();
    }

    // geht nicht weil da geschaut wird ob es genau das selbe Object ist und dnicht die essage verglichen wird
    // damit das funktioniert muss man ein equals und hashcode hinzugefügt werden in die HelloWorld2
    //um sich diesen ganzen Code zu ersparren gibt es Records mnn kann in Records auch noch zusätzlich Attribute und Methoden hinzufügen

    /*@Test
    void normalClass()
    {
        var helloWorld2_1 = new HelloWorld2("xxx");
        var helloWorld2_2 = new HelloWorld2("xxx");
        assertThat(helloWorld2_1.equals(helloWorld2_2)).isTrue();
    }*/


    public static class HelloWorld2 {
        private String message;

        private HelloWorld2(String message)
        {
            setMessage(message);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}