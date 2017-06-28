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

public class MensajeTest extends TestCase {

    @Autowired
    Mensaje mensaje;

    @Before
    public void setUp() {
        mensaje = new Mensaje("lisandro@gmail.com", "cooper@gmail.com", "asunto", "texto largo aqui");
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

    @Test
    public void testRecipiente() {
        assertEquals("Checking getrecipiente()", mensaje.getRecipiente(), "cooper@gmail.com");
    }

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
}