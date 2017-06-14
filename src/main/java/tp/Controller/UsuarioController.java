package tp.Controller;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import tp.Request.UsuarioRequest;
import tp.Services.UsuarioService;
import tp.Model.Usuario;

@RestController
public class UsuarioController
{
    @Autowired
    UsuarioService usuarioService;

    //ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    /*public UsuarioController(){
        Usuario user = new Usuario("Lisandro", "Cooper", "Arana y Goiri 3120", "02234850887", "Mar del Plata",
                "Argentina", "Buenos Aires", "sec765", "coopermegadeth@gmail.com", "lisandrocooper1995@gmail.com");
        usuarios.add(user);
    }*/

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Usuario> getAllUsers() {
        ArrayList<Usuario> usuarios = usuarioService.todosUsuariosService();
        return usuarios;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crearUsuario(@RequestBody UsuarioRequest request) {
        Usuario user = new Usuario(request.getNombre(), request.getApellido(), request.getDireccion(), request.getTelefono(), request.getCiudad(),
                request.getPais(), request.getProvincia(), request.getContra(), request.getEmail(), request.getEmail2());
        usuarioService.agregarUsuarioService(user);
    }

}