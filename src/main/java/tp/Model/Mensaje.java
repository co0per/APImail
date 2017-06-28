package tp.Model;

import java.sql.Timestamp;
import java.util.Date;

public class Mensaje {
    int id;
    String remitente_mail;
    String recipiente_mail;
    String asunto;
    String mensaje;
    Timestamp fecha;
    Boolean borrado;

    public Mensaje (String rem, String rec, String a, String m){
        remitente_mail = rem;
        recipiente_mail = rec;
        asunto = a;
        mensaje = m;
        /*Date date = new Date();
        fecha = new Timestamp(date.getTime());*/
        borrado = false;
    }

    public Mensaje() {}

    public String getRemitente() { return remitente_mail; }
    public void setRemitente(String remitente) { this.remitente_mail = remitente; }
    public String getRecipiente() { return recipiente_mail; }
    public void setRecipiente(String recipiente) { this.recipiente_mail = recipiente; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Boolean getBorrado() { return borrado; }
    public void setBorrado(Boolean borrado) { this.borrado = borrado; }
}
