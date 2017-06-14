package tp.Model;

import java.util.Date;

public class Mensaje {
    Usuario remitente;
    Usuario recipiente;
    String asunto;
    String mensaje;
    Date fecha;

    public Mensaje (Usuario rem, Usuario rec, String a, String m, Date d){
        remitente = rem;
        recipiente = rec;
        asunto = a;
        mensaje = m;
        fecha = d;
    }

    public Usuario getRemitente() { return remitente; }
    public void setRemitente(Usuario remitente) { this.remitente = remitente; }
    public Usuario getRecipiente() { return recipiente; }
    public void setRecipiente(Usuario recipiente) { this.recipiente = recipiente; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
