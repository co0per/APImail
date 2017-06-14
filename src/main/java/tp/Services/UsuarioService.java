package tp.Services;

import tp.DAO.UsuarioDAO;
import tp.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    UsuarioDAO usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDAO dao) {
        this.usuarioDao = dao;
    }

    @Autowired
    public ArrayList<Usuario> todosUsuariosService(){
        return usuarioDao.traerTodosUsers();
    }

    /*
    public Usuario login(String nombreUsuario, String password) {
        return usuarioDao.get(nombreUsuario,password);
    }*/
}