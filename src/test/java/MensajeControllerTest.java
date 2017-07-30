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
    private Mensaje mensa;
    private Usuario usuario;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();

        usuario = new Usuario("Gordon", "Freeman", "somewhere", "1234", "Lago del Terror", "EEUU",
                "Texas", "password", "coopermegadeth@gmail.com", "gordo@gmail.com");
        usuario.setID(1);

        ArrayList<String> lista = new ArrayList<String>();
        lista.add("charles_bronson@gmail.com");
        lista.add("hola@gmail.com");
        mensaje = new Mensaje("coopermegadeth@gmail.com", lista, "asunto", "texto largo aqui");
        mensaje.setId(1);

        mensa = new Mensaje("coopermegadeth@gmail.com", lista, "asunto", "texto largo aqui");
        mensa.setId(2);
        mensa.setBorrado(true);

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
                        .param("recipiente", "coopermegadeth@gmail.com")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetInboxNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid",this.sessionid)
                        .param("recipiente", "nadie@gmail.com")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetInboxBadRequest() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/inbox")
                        .header("sessionid", this.sessionid)
                        .param("recipientex", "1")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetMensajesEnviadosOk() throws Exception {
        mockMvc.perform(
                get("/api/mensajes")
                        .header("sessionid", this.sessionid)
                        .param("remitente", "coopermegadeth@gmail.com")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetMensajesEnviadosNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensajes")
                        .header("sessionid", this.sessionid)
                        .param("remitente", "nadie@gmail.com")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetMensajesEnviadosBadRequest() throws Exception {
        mockMvc.perform(
                get("/api/mensajes")
                        .header("sessionid", this.sessionid)
                        .param("remitentez", "255")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEnviarMensajeOk() throws Exception{
        URL url  = Resources.getResource("mensaje.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        mockMvc.perform(
                post("/api/enviar")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testEnviarMensajeBadRequest() throws Exception {
        mockMvc.perform(
                post("/api/enviar")
                        .header("sessionid", this.sessionid)

        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBorrarMensajeOk() throws Exception {
        mockMvc.perform((
                        delete("/api/mensaje")
                                .header("sessionid", this.sessionid)
                                .param("id_mensaje", "1")
                )
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void testBorrarMensajeBadRequest() throws Exception {
        mockMvc.perform((
                        delete("/api/mensaje")
                                .header("sessionid", this.sessionid)
                                .param("id_mensaje", "whatever")
                )
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetEliminadosOk() throws Exception{
        mockMvc.perform(
                get("/api/mensajes/eliminados")
                        .header("sessionid", this.sessionid)
                        .param("recipiente", "charles_bronson@gmail.com")

        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEliminadosNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensajes/eliminados")
                        .header("sessionid", this.sessionid)
                        .param("recipiente", "33")

        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetEliminadosBadRequest() throws Exception{
        mockMvc.perform(
                get("/api/mensajes/eliminados")
                        .header("sessionid", this.sessionid)
                        .param("recipienteX", "33")

        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLeerMensajeOk() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/leer")
                        .header("sessionid", this.sessionid)
                        .param("id_mensaje", "93")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testLeerMensajeLeidoOk() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/leer")
                        .header("sessionid", this.sessionid)
                        .param("id_mensaje", "65")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testLeerMensajeNoContent() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/leer")
                        .header("sessionid",this.sessionid)
                        .param("id_mensaje", "1000")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testLeerMensajeBadRequest() throws Exception{
        mockMvc.perform(
                get("/api/mensaje/leer")
                        .header("sessionid", this.sessionid)
                        .param("id_mensajeX", "1")
        )
                .andExpect(status().isBadRequest());
    }
}
