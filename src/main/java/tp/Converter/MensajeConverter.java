package tp.Converter;

import tp.Model.Mensaje;
import tp.Wrapper.MensajeWrapper;
import tp.Wrapper.MensajeWrapperLeer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MensajeConverter {
    public MensajeWrapper convert(Mensaje message)
    {
        MensajeWrapper message_wrapper = new MensajeWrapper();
        message_wrapper.setRemitente(message.getRemitente());
        message_wrapper.setRecipiente(message.getRecipientes());
        message_wrapper.setAsunto(message.getAsunto());
        //message_wrapper.setMensaje(message.getMensaje());
        message_wrapper.setFecha(message.getFecha());
        message_wrapper.setLeido(message.getLeido());
        message_wrapper.setBorrado(message.getBorrado());

        return message_wrapper;
    }

    public MensajeWrapperLeer convertLeer(Mensaje message)
    {
        MensajeWrapperLeer message_wrapper = new MensajeWrapperLeer();
        message_wrapper.setRemitente(message.getRemitente());
        message_wrapper.setRecipiente(message.getRecipientes());
        message_wrapper.setAsunto(message.getAsunto());
        message_wrapper.setMensaje(message.getMensaje());
        message_wrapper.setFecha(message.getFecha());
        //message_wrapper.setLeido(message.getLeido());
        message_wrapper.setBorrado(message.getBorrado());

        return message_wrapper;
    }
}
