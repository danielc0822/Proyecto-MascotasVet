/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Daniel
 */
public class Mascota {

    private int idMascota;
    private String Nombre;
    private String Genero;
    private String Tipo;
    private String Raza;
    private boolean Activo;
    private int idDueno;

    public Mascota() {
    }

    public Mascota(int idMascota, String Nombre, String Genero, String Tipo, String Raza, boolean Activo, int idDueno) {
        this.idMascota = idMascota;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Tipo = Tipo;
        this.Raza = Raza;
        this.Activo = Activo;
        this.idDueno = idDueno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

}
