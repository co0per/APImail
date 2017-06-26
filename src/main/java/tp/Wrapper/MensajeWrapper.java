package tp.Wrapper;

import tp.Model.Mensaje;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp.Model.Usuario;

import java.util.Date;

public class MensajeWrapper {
    @JsonProperty
    String remitente;
    @JsonProperty
    String recipiente;
    @JsonProperty
    String asunto;
    @JsonProperty
    String mensaje;
    @JsonProperty
    Date fecha;

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }
    public String getRecipiente() { return recipiente; }
    public void setRecipiente(String recipiente) { this.recipiente = recipiente; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
