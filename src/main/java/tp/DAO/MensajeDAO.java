package tp.DAO;

import org.springframework.stereotype.Repository;
import tp.Model.Usuario;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tp.Model.Mensaje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tp.util.SessionData;

@Repository
public class MensajeDAO
{
    Connection conn;

    //@Autowired
    public MensajeDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/APImail", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enviarMail(Mensaje message){
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO mensajes (remitente, recipiente, asunto, mensaje, " +
                    "fecha, borrado) VALUES (?,?,?,?,?, 0)");
            stat.setString(1, message.getRemitente());
            stat.setString(2, message.getRecipiente());
            stat.setString(3, message.getAsunto());
            stat.setString(4, message.getMensaje());
            stat.setTimestamp(5, message.getFecha());
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> traerEnviados(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM mensajes where remitente = ? " +
                    "and borrado = 0");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();


            while(resu.next())
            {
                Mensaje mensa = new Mensaje(resu.getString("remitente"), resu.getString("recipiente"),
                        resu.getString("asunto"), resu.getString("mensaje"));
                mensa.setId(resu.getInt("id"));
                mensa.setFecha(resu.getTimestamp("fecha"));
                lista_loca.add(mensa);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista_loca;
    }

    public ArrayList<Mensaje> traerBorrados(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM mensajes where recipiente = ? " +
                    "and borrado = 1");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();


            while(resu.next())
            {
                Mensaje mensa = new Mensaje(resu.getString("remitente"), resu.getString("recipiente"),
                        resu.getString("asunto"), resu.getString("mensaje"));
                mensa.setId(resu.getInt("id"));
                mensa.setBorrado(true);
                mensa.setFecha(resu.getTimestamp("fecha"));
                lista_loca.add(mensa);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista_loca;
    }

    public void borrar(int id)
    {
        try {
            PreparedStatement stat = conn.prepareStatement("UPDATE mensajes SET borrado = 1 WHERE id = ?");
            stat.setInt(1, id);
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> traerInbox(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM mensajes where recipiente = ? " +
                    "and borrado = 0");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();


            while(resu.next())
            {
                Mensaje mensa = new Mensaje(resu.getString("remitente"), resu.getString("recipiente"),
                        resu.getString("asunto"), resu.getString("mensaje"));
                mensa.setId(resu.getInt("id"));
                mensa.setBorrado(false);
                mensa.setFecha(resu.getTimestamp("fecha"));
                lista_loca.add(mensa);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista_loca;
    }
}