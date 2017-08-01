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

    private int getLast_mensaje_id(){
        int id = 0;
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM mensajes ORDER BY id DESC LIMIT 1");
            ResultSet resu = stat.executeQuery();

            while(resu.next()) {
                id = resu.getInt("id");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void enviarMail(Mensaje message)
    {
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO mensajes (remitente, asunto, mensaje, " +
                    "fecha, borrado, leido) VALUES (?,?,?,?, 0, 0)");
            stat.setString(1, message.getRemitente());
            stat.setString(2, message.getAsunto());
            stat.setString(3, message.getMensaje());
            stat.setTimestamp(4, message.getFecha());
            stat.execute();

            this.getLast_mensaje_id();

            for(int i = 0; message.getRecipientes().size() > i; i++){
                stat = conn.prepareStatement("INSERT INTO mensaje_x_usuario (id_mensaje, id_usuario) VALUES (?,?)");
                stat.setInt(1, this.getLast_mensaje_id());
                stat.setString(2, message.getRecipientes().get(i));
                stat.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> traerEnviados(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM mensajes where remitente = ? ORDER BY fecha DESC");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();

            while(resu.next())
            {
                ArrayList<String> lista_usuarios;
                lista_usuarios = get_usuarios_p_mensaje(resu.getInt("id"));

                Mensaje mensa = new Mensaje(resu.getString("remitente"), lista_usuarios,
                        resu.getString("asunto"), resu.getString("mensaje"));
                mensa.setId(resu.getInt("id"));
                mensa.setFecha(resu.getTimestamp("fecha"));
                /*System.out.println("facho" + resu.getTimestamp("fecha"));
                Date dia = new Date(resu.getTimestamp("fecha").getTime());
                System.out.println("facha" + dia);*/
                mensa.setBorrado(resu.getBoolean("borrado"));
                mensa.setLeido(resu.getBoolean("leido"));


                lista_loca.add(mensa);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista_loca;
    }

    private ArrayList<String> get_usuarios_p_mensaje (int id_mensaje) //devuelve lista de recipientes de un mensaje
    {
        ArrayList<String> lista_usuarios = new ArrayList<String>();
        try{
            PreparedStatement stat = conn.prepareStatement("SELECT id_usuario FROM mensaje_x_usuario WHERE id_mensaje = ?");
            stat.setInt(1, id_mensaje);
            ResultSet resu_ids_users = stat.executeQuery();

            while(resu_ids_users.next()){
                lista_usuarios.add(resu_ids_users.getString("id_usuario"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return lista_usuarios;
    }

    public ArrayList<Mensaje> traerBorrados(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            //primero traigo los id's de los mensajes de un usuario desde la tabla intermediaria de la BD
            PreparedStatement stat = conn.prepareStatement("SELECT id_mensaje FROM mensaje_x_usuario WHERE " +
                    "id_usuario = ?");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();

            while(resu.next()) { //traigo los mensajes a partir de sus id's...
                stat = conn.prepareStatement("SELECT * FROM mensajes WHERE id = ? and borrado = 1 ORDER BY fecha DESC");
                stat.setInt(1, resu.getInt("id_mensaje"));
                ResultSet resultado = stat.executeQuery();

                while (resultado.next()) { //creo los objetos de tipo mensaje
                    ArrayList<String> lista_usuarios;
                    lista_usuarios = get_usuarios_p_mensaje(resultado.getInt("id"));

                    Mensaje mensa = new Mensaje(resultado.getString("remitente"), lista_usuarios,
                            resultado.getString("asunto"), resultado.getString("mensaje"));
                    mensa.setId(resultado.getInt("id"));
                    mensa.setBorrado(true);

                    if (resultado.getBoolean("leido") == false) {
                        mensa.setLeido(false);
                    } else {
                        mensa.setLeido(true);
                    }
                    mensa.setFecha(resultado.getTimestamp("fecha"));
                    lista_loca.add(mensa);
                }
            }
        }catch (SQLException e){
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

    public void setLeido(int id_mensaje){
        try {
            PreparedStatement stat = conn.prepareStatement("UPDATE mensajes SET leido = 1 WHERE id = ?");
            stat.setInt(1, id_mensaje);
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mensaje> leerMensaje(int id_mensaje){ //trabajo con una lista para hacerla mas facil
        ArrayList<Mensaje> mensaje = new ArrayList<Mensaje>();
        try{
            PreparedStatement stat = conn.prepareStatement("SELECT * from mensajes where id = ? and borrado = 0");
            stat.setInt(1, id_mensaje);
            ResultSet resultado = stat.executeQuery();

            while(resultado.next())
            {
                ArrayList<String> lista_usuarios = get_usuarios_p_mensaje(id_mensaje);
                Mensaje mensa = new Mensaje(resultado.getString("remitente"), lista_usuarios,
                        resultado.getString("asunto"), resultado.getString("mensaje"));
                if (resultado.getBoolean("leido") == false) {
                    this.setLeido(resultado.getInt("id"));
                    mensa.setLeido(false);
                } else /*if (resultado.getBoolean("leido"))*/ {
                    mensa.setLeido(true);
                } /*else {
                    this.setLeido(resultado.getInt("id"));
                    mensaje.setLeido(false);
                }*/
                mensa.setFecha(resultado.getTimestamp("fecha"));

                mensaje.add(mensa);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return mensaje;
    }

    public ArrayList<Mensaje> traerInbox(String mail)
    {
        ArrayList<Mensaje> lista_loca = new ArrayList<Mensaje>();
        try {
            //primero traigo los id's de los mensajes de un usuario desde la tabla intermediaria de la BD
            PreparedStatement stat = conn.prepareStatement("SELECT id_mensaje FROM mensaje_x_usuario WHERE " +
                    "id_usuario = ?");
            stat.setString(1, mail);
            ResultSet resu = stat.executeQuery();

            while(resu.next()) { //traigo los mensajes a partir de sus id's...
                stat = conn.prepareStatement("SELECT * FROM mensajes WHERE id = ? and borrado = 0 ORDER BY fecha DESC");
                stat.setInt(1, resu.getInt("id_mensaje"));
                ResultSet resultado = stat.executeQuery();

                while (resultado.next()) { //creo los objetos de tipo mensaje
                    ArrayList<String> lista_usuarios;
                    lista_usuarios = get_usuarios_p_mensaje(resultado.getInt("id"));

                    Mensaje mensa = new Mensaje(resultado.getString("remitente"), lista_usuarios,
                            resultado.getString("asunto"), resultado.getString("mensaje"));
                    mensa.setId(resultado.getInt("id"));
                    mensa.setBorrado(false);

                    if (resultado.getBoolean("leido") == false) {
                        //this.setLeido(resultado.getInt("id"));
                        mensa.setLeido(false);
                    } else if (resultado.getBoolean("leido")) {
                        mensa.setLeido(true);
                    } /*else {
                        this.setLeido(resultado.getInt("id"));
                        mensa.setLeido(false);
                    }*/
                    mensa.setFecha(resultado.getTimestamp("fecha"));
                    lista_loca.add(mensa);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista_loca;
    }
}