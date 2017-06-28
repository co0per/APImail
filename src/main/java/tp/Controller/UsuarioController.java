package tp.Controller;

/**
 * Created by co0per on 10/06/1992.
 */

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import tp.Request.UsuarioRequest;
import tp.Services.UsuarioService;
import tp.Model.Usuario;
import tp.util.SessionData;
import tp.Wrapper.LoginWrapper;
import tp.Wrapper.UsuarioWrapper;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController
{
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<LoginWrapper> login(@RequestParam(value = "nombre", required = true) String nombre,
                                         @RequestParam(value = "contra", required = true) String password) {
        Usuario usuario = new Usuario();
        try {
            usuario = usuarioService.login(nombre, password);
        } catch (Exception e) {
            return new ResponseEntity<LoginWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (usuario.getEmail() == null) {
            return new ResponseEntity<LoginWrapper>(HttpStatus.FORBIDDEN);
        }
        if (null != usuario) {
            String sessionID = sessionData.addSession(usuario);
            return new ResponseEntity<LoginWrapper>(new LoginWrapper(sessionID), HttpStatus.OK);
        }
        return new ResponseEntity<LoginWrapper>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping("/logout")
    public
    @ResponseBody
    ResponseEntity logout(@RequestHeader("sessionid") String sessionID) {
        sessionData.removeSession(sessionID);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<ArrayList<UsuarioWrapper>> getAllUsers() {
        try {
            ArrayList<UsuarioWrapper> usuarios = usuarioService.todosUsuariosService();
            if (usuarios.isEmpty()) {
                return new ResponseEntity<ArrayList<UsuarioWrapper>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<UsuarioWrapper>>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ArrayList<UsuarioWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity crearUsuario(@RequestBody UsuarioRequest request) {
        try {
            Usuario user = new Usuario(request.getNombre(), request.getApellido(), request.getDireccion(),
                    request.getTelefono(), request.getCiudad(), request.getPais(), request.getProvincia(),
                    request.getContra(), request.getEmail(), request.getEmail2());
            usuarioService.agregarUsuarioService(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity borrarUsuario(@RequestParam("id") int id) {
        try {
            usuarioService.borrarUsuarioService(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<UsuarioWrapper> getUsusario_x_Nombre(@RequestParam(value = "nombre") String nombre) {                                                   // Traer Usuario por nombre
        try {
            UsuarioWrapper usuarioWrapper = usuarioService.getByNameService(nombre);
            if (usuarioWrapper.getNombre() == null) {
                return new ResponseEntity<UsuarioWrapper>(HttpStatus.NO_CONTENT);
            }else
            {
                return new ResponseEntity<UsuarioWrapper>(usuarioWrapper, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<UsuarioWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}