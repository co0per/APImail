import tp.Application;
import tp.Model.Mensaje;
import tp.Model.Usuario;
import tp.Wrapper.MensajeWrapper;
import tp.util.SessionData;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
public class MensajeControllerTest extends TestCase {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private Mensaje mensaje;
    private Usuario usuario;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();

        usuario = new Usuario("Gordon", "Freeman", "somewhere", "1234", "Lago del Terror", "EEUU",
                "Texas", "password", "lisandro@gmail.com", "gordo@gmail.com");
        usuario.setID(1);

        mensaje = new Mensaje("lisandro@gmail.com", "cooper@gmail.com", "asunto", "texto largo aqui");
        mensaje.setId(1);

        this.sessionid = this.sessionData.addSession(usuario);
    }

    @After
    public void tearDown() {
        this.sessionData.removeSession(this.sessionid);
    }

    @Test
    public void testGetInboxOk() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid", this.sessionid)
                        .param("recipiente", "7")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    /*@Test
    public void testGetInboxNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid",this.sessionid)
                        .param("recipienter", "nadie@gmail.com")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetInboxBadRequest() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid", this.sessionid)
                        .param("recipiente", "1")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetSendOk() throws Exception {
        mockMvc.perform(
                get("/api/mensaje")
                        .header("sessionid", this.sessionid)
                        .param("recipiente", "lisandro@gmail.com")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetSendNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensaje")
                        .header("sessionid", this.sessionid)
                        .param("remitente", "nadie@gmail.com")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetSendBadRequest() throws Exception {
        mockMvc.perform(
                get("/api/mensaje")
                        .header("sessionid", this.sessionid)
                        .param("remitente", "255")
        )
                .andExpect(status().isBadRequest());
    }*/
}
