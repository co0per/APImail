package tp.Model;

public class Usuario {
    int id;
    String nombre;
    String apellido;
    String direccion;
    String telefono;
    String ciudad;
    String pais;
    String provincia;
    String contra;
    String email;
    String email2;

    public Usuario(){
        email = "";
    }

    public Usuario(String n, String a, String d, String t, String c, String pa, String pro, String cont, String e, String e2){
        nombre = n;
        apellido = a;
        direccion = d;
        telefono = t;
        ciudad = c;
        pais = pa;
        provincia = pro;
        contra = cont;
        email = e;
        email2 = e2;
    }

    public void setID(int id) { this.id = id; }
    public int getID() { return id; }
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
}