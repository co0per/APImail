/**
 * Created by co0per on 27/06/17.
 */

import tp.Model.Usuario;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class UsuarioTest extends TestCase {

    @Autowired
    Usuario usuario;

    @Before
    public void setUp(){
        usuario = new Usuario("Gordon", "Freeman", "somewhere", "1234", "Lago del Terror", "EEUU",
                "Texas", "password", "lisandro@gmail.com", "gordo@gmail.com");
        usuario.setID(1);
    }

    @Test
    public void testNombre(){
        assertEquals("Checking getNombre()", usuario.getNombre(), "Gordon");
    }

    @Test
    public void testApellido(){
        assertEquals("Checking getApellido()", usuario.getApellido(), "Freeman");
    }

    @Test
    public void testDireccion(){
        assertEquals("Checking getDireccion()", usuario.getDireccion(), "somewhere");
    }

    @Test
    public void testTelefono(){
        assertEquals("Checking getTelefono()", usuario.getTelefono(), "1234");
    }

    @Test
    public void testCiudad(){
        assertEquals("Checking getCiudad()", usuario.getCiudad(), "Lago del Terror");
    }

    @Test
    public void testPais(){
        assertEquals("Checking getPais()", usuario.getPais(), "EEUU");
    }

    @Test
    public void testProvincia(){
        assertEquals("Checking getProvincia()", usuario.getProvincia(), "Texas");
    }

    @Test
    public void testPass(){
        assertEquals("Checking getContra()", usuario.getContra(), "password");
    }

    @Test
    public void testEmail(){ assertEquals("Checking getEmail()", usuario.getEmail(), "lisandro@gmail.com"); }

    @Test
    public void testEmail2(){ assertEquals("Checking getEmail2()", usuario.getEmail2(), "gordo@gmail.com"); }
}
