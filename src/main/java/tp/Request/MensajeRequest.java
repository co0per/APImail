package tp.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tp.Model.Mensaje;

import java.sql.Timestamp;

public class MensajeRequest {
    public String getRemitente() { return remitente_mail; }
    public void setRemitente(String remitente) { this.remitente_mail = remitente; }
    public String getRecipiente() { return recipiente_mail; }
    public void setRecipiente(String recipiente) { this.recipiente_mail = recipiente; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    /*public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
*/
    @JsonProperty("remitente_mail")
    String remitente_mail;
    @JsonProperty("recipiente_mail")
    String recipiente_mail;
    @JsonProperty("asunto")
    String asunto;
    @JsonProperty("mensaje")
    String mensaje;
    /*@JsonProperty("fecha")
    Timestamp fecha;*/
}
