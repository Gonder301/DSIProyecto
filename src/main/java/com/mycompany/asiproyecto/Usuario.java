
package com.mycompany.asiproyecto;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;
    
    public Usuario() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int _id) {
        id = _id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String _nombre) {
        nombre = _nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String _email) {
        email = _email;
    }
    
    public void setContrasena(String _contrasena) {
        rol = _contrasena;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String _rol) {
        rol = _rol;
    }
}
