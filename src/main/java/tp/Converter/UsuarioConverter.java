package tp.Converter;

import tp.Model.Usuario;
import tp.Wrapper.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class UsuarioConverter {
    public UsuarioWrapper convert(Usuario persona)
    {
        UsuarioWrapper user_wrapper = new UsuarioWrapper(persona.getNombre(), persona.getApellido(), persona.getDireccion(),
                persona.getTelefono(), persona.getCiudad(), persona.getPais(), persona.getProvincia(), persona.getEmail(),
                persona.getEmail2());
        return user_wrapper;
    }
}
