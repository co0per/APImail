package tp.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Mensaje {
    int id;
    String remitente_mail;
    ArrayList<String> recipientes_mails;
    String asunto;
    String mensaje;
    Timestamp fecha;
    Boolean borrado;
    Boolean leido;

    public Mensaje (String rem, ArrayList<String> rec, String a, String m){
        remitente_mail = rem;
        recipientes_mails = rec;
        asunto = a;
        mensaje = m;
        /*Date date = new Date();
        fecha = new Timestamp(date.getTime());*/
        borrado = false;
        leido = false;
    }

    public Mensaje() {}

    public String getRemitente() { return this.remitente_mail; }
    public void setRemitente(String remitente) { this.remitente_mail = remitente; }
    public ArrayList<String> getRecipientes() { return this.recipientes_mails; }
    public void setRecipientes(ArrayList<String> recipiente) { this.recipientes_mails = recipiente; }
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
    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
}