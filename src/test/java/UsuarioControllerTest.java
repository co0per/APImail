import tp.Application;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import tp.Model.Mensaje;
import tp.Model.Usuario;
import tp.Wrapper.UsuarioWrapper;
import tp.Services.UsuarioService;
import tp.util.SessionData;
import junit.framework.TestCase;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration
@ActiveProfiles("default")
public class UsuarioControllerTest {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UsuarioService usuarioService;

    private MockMvc mockMvc;
    private String sessionid;
    private Usuario usuario;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();

        usuario = new Usuario("Gordon", "Freeman", "somewhere", "1234", "Lago del Terror", "EEUU",
                "Texas", "password", "lisandro@gmail.com", "gordo@gmail.com");
        usuario.setID(1);

        this.sessionid = this.sessionData.addSession(usuario);
    }

    @After
    public void tearDown() throws Exception{
        this.sessionData.removeSession(this.sessionid);
    }

    @Test
    public void testCrearUsuarioCreated() throws Exception{
        URL url  = Resources.getResource("usuario.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(
                post("/api/user")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetUsusario_x_NombreOk() throws Exception{
        mockMvc.perform(
                get("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("nombre", "lisandro")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsusario_x_NombreNoContent() throws Exception{
        mockMvc.perform(
                get("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("nombre", "zapatilla")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testBorrarUsuario() throws Exception{
        mockMvc.perform(
                delete("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("id", "1")
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetAllUsersOk() throws Exception{
        mockMvc.perform(
                get("/api/users")
                        .header("sessionid", this.sessionid)

        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetAllUsersNoContent() throws Exception {
        usuarioService = mock(UsuarioService.class);
        when(usuarioService.todosUsuariosService()).thenReturn(new ArrayList<UsuarioWrapper>());
    }

    @Test
    public void testBorrarUsuarioBadRequest() throws Exception{

        mockMvc.perform(delete
                ("/api/user/")
                .header("sessionid", this.sessionid)
                .param("id", "cola")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLoginOk() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("nombre", "lisandro"),
                                new BasicNameValuePair("contra", "123")
                        ))))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginForbidden() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("nombre", "rompe todo hermano"),
                                new BasicNameValuePair("contra", "dale nomas")
                        ))))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    public void testLogOutOk() throws Exception{

        mockMvc.perform(post("/logout")
                .header("sessionid", this.sessionid)

        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldReturnHttpCode405OnPUT() throws Exception{
        mockMvc.perform(
                put("/api/user")
        )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldReturnHttpCode400PostWithoutParameter() throws Exception{
        mockMvc.perform(
                post("/login")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400getByNameWithoutParameter() throws Exception{
        mockMvc.perform(
                get("/api/user")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkSessionTest() throws Exception {
        this.sessionData.checkSessions();
    }
}
