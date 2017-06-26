package tp.Services;

import tp.DAO.UsuarioDAO;
import tp.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import tp.Wrapper.UsuarioWrapper;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDAO dao) {
        this.usuarioDao = dao;
    }

    public ArrayList<UsuarioWrapper> todosUsuariosService(){ return usuarioDao.traerTodosUsers(); }

    public void agregarUsuarioService(Usuario user) { usuarioDao.agregarUser(user); }

    public Usuario login(String nombre, String pass) throws SQLException {
        try {
            return usuarioDao.getUser4Login(nombre, pass);
        } catch (SQLException e) {
            throw e;
        }
    }

    public UsuarioWrapper getByNameService(String nombre) {
        UsuarioWrapper user = usuarioDao.getByName(nombre);
        return user;
    }

    public void borrarUsuarioService(int id) { usuarioDao.borrarUser(id); }
}