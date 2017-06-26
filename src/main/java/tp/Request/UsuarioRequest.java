package tp.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestParam;

public class UsuarioRequest {

    public int setID() { return id; }
    public void getID(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getContra() { return contra; }
    public void setContra(String contra) { this.contra = contra; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail2() { return email2; }
    public void setEmail2(String email2) { this.email2 = email2; }

    @JsonProperty("id")
    int id;
    @JsonProperty("nombre")
    String nombre;
    @JsonProperty("apellido")
    String apellido;
    @JsonProperty("direccion")
    String direccion;
    @JsonProperty("telefono")
    String telefono;
    @JsonProperty("ciudad")
    String ciudad;
    @JsonProperty("pais")
    String pais;
    @JsonProperty("provincia")
    String provincia;
    @JsonProperty("contra")
    String contra;
    @JsonProperty("email")
    String email;
    @JsonProperty("email2")
    String email2;
}
