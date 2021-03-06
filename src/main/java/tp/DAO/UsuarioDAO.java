package tp.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tp.Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tp.util.SessionData;
import tp.Wrapper.UsuarioWrapper;

@Repository
public class UsuarioDAO
{
    Connection conn;

    //@Autowired
    public UsuarioDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/APImail?serverTimezone=UTC",
                    "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void agregarUser(Usuario user){
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO usuarios (nombre, apellido, direccion, telefono, " +
                    "ciudad, pais, provincia, contra, email, email2) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1,user.getNombre());
            stat.setString(2,user.getApellido());
            stat.setString(3, user.getDireccion());
            stat.setString(4, user.getTelefono());
            stat.setString(5, user.getCiudad());
            stat.setString(6, user.getPais());
            stat.setString(7, user.getProvincia());
            stat.setString(8, user.getContra());
            stat.setString(9, user.getEmail());
            stat.setString(10, user.getEmail2());
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UsuarioWrapper> traerTodosUsers()
    {
        ArrayList<UsuarioWrapper> lista_loca = new ArrayList<UsuarioWrapper>();
        try {
            Statement stat = conn.createStatement();
            ResultSet resu = stat.executeQuery("SELECT * FROM usuarios");

            while(resu.next())
            {
                UsuarioWrapper mitch = new UsuarioWrapper(resu.getString("nombre"), resu.getString("apellido"),
                        resu.getString("direccion"), resu.getString("telefono"), resu.getString("ciudad"),
                        resu.getString("pais"), resu.getString("provincia"), resu.getString("email"),
                        resu.getString("email2"));

                lista_loca.add(mitch);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista_loca;
    }

    public Usuario getUser4Login(String nombre, String pass) throws SQLException{
        Usuario user = new Usuario();
        try{
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contra = ?");
            stat.setString(1,nombre);
            stat.setString(2,pass);
            ResultSet resu = stat.executeQuery();
            while(resu.next()){
                user.setID(resu.getInt("id"));
                user.setNombre(resu.getString("nombre"));
                user.setApellido(resu.getString("apellido"));
                user.setDireccion(resu.getString("direccion"));
                user.setTelefono(resu.getString("telefono"));
                user.setCiudad(resu.getString("ciudad"));
                user.setPais(resu.getString("pais"));
                user.setProvincia(resu.getString("provincia"));
                user.setContra(resu.getString("contra"));
                user.setEmail(resu.getString("email"));
                user.setEmail2(resu.getString("email2"));
                System.out.println(user);
            }
        }catch (SQLException e){
            throw e;
        }
        System.out.println("mierda seca ");
        return user;
    }

    public void borrarUser(int id){
        try {
            PreparedStatement stat = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            stat.setInt(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UsuarioWrapper getByName(String nombre) {
        UsuarioWrapper user = new UsuarioWrapper();
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");
            stat.setString(1,nombre);
            ResultSet resu = stat.executeQuery();
            while(resu.next()){
                user.setNombre(resu.getString("nombre"));
                user.setApellido(resu.getString("apellido"));
                user.setDireccion(resu.getString("direccion"));
                user.setTelefono(resu.getString("telefono"));
                user.setCiudad(resu.getString("ciudad"));
                user.setPais(resu.getString("pais"));
                user.setProvincia(resu.getString("provincia"));
                user.setEmail(resu.getString("email"));
                user.setEmail2(resu.getString("email2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}