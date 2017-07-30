/**
 * Created by co0per on 27/06/17.
 */

import tp.Model.Mensaje;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

public class MensajeTest extends TestCase {

    @Autowired
    Mensaje mensaje;

    @Before
    public void setUp() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("charles_bronson@gmail.com");
        lista.add("hola@gmail.com");
        mensaje = new Mensaje("lisandro@gmail.com", lista, "asunto", "texto largo aqui");
        mensaje.setId(1);
    }

    @Test
    public void testID() {
        assertEquals("Checking getID()", mensaje.getId(), 1);
    }

    @Test
    public void testRemitente() {
        assertEquals("Checking getremitente()", mensaje.getRemitente(), "lisandro@gmail.com");
    }
/*
    @Test
    public void testRecipiente() {
        assertEquals("Checking getrecipiente()", mensaje.getRecipientes(), );
    }*/

    @Test
    public void testAsunto() {
            assertEquals("Checking getAsunto()", mensaje.getAsunto(), "asunto");
    }

    @Test
    public void testMensaje() {
        assertEquals("Checking getMensaje()", mensaje.getMensaje(), "texto largo aqui");
    }

    @Test
    public void testBorrado() {
        assertFalse("Checking getBorrado()", mensaje.getBorrado());
    }

    @Test
    public void testLeido() {
        assertFalse("Checking getLeido()", mensaje.getLeido());
    }
}