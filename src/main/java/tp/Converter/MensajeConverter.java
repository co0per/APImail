package tp.Converter;

import tp.Model.Mensaje;
import tp.Wrapper.MensajeWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MensajeConverter {
    public MensajeWrapper convert(Mensaje message)
    {
        MensajeWrapper message_wrapper = new MensajeWrapper();
        message_wrapper.setRemitente(message.getRemitente());
        message_wrapper.setRecipiente(message.getRecipiente());
        message_wrapper.setAsunto(message.getAsunto());
        message_wrapper.setMensaje(message.getMensaje());
        message_wrapper.setFecha(message.getFecha());

        return message_wrapper;
    }
}
