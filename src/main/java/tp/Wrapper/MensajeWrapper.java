package tp.Wrapper;

import tp.Model.Mensaje;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp.Model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class MensajeWrapper {
    @JsonProperty
    String remitente_mail;
   /* @JsonProperty
    ArrayList<String> recipientes_mail;*/
    @JsonProperty
    String asunto;
    @JsonProperty
    String fecha;
    @JsonProperty
    Boolean leido;
    @JsonProperty
    Boolean borrado;

    public String getRemitente() { return this.remitente_mail; }
    public void setRemitente(String remitente) { this.remitente_mail = remitente; }
    /*public ArrayList<String> getRecipiente() { return this.recipientes_mail; }
    public void setRecipiente(ArrayList<String> recipiente) { this.recipientes_mail = recipiente; }*/
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setLeido(Boolean leido) { this.leido = leido; }
    public Boolean getLeido() { return leido; }
    public void setBorrado(Boolean borradin) { this.borrado = borradin; }
    public Boolean getBorrado() { return borrado; }
}