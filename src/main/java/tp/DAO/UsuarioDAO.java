package tp.DAO;

import tp.Model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO
{
    Connection conn;

    static UsuarioDAO instance;

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public UsuarioDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/APImail", "root", "1234");

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

    public ArrayList<Usuario> traerTodosUsers()
    {
        ArrayList<Usuario> lista_loca = new ArrayList<Usuario>();
        try {
            Statement stat = conn.createStatement();
            ResultSet resu = stat.executeQuery("SELECT * FROM usuarios");

            while(resu.next())
            {
                Usuario mitch = new Usuario(resu.getString("nombre"), resu.getString("apellido"),
                        resu.getString("direccion"), resu.getString("telefono"), resu.getString("ciudad"),
                        resu.getString("pais"), resu.getString("provincia"), resu.getString("contra"),
                        resu.getString("email"), resu.getString("2email"));
                lista_loca.add(mitch);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista_loca;
    }
}
