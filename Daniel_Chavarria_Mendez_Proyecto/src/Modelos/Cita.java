/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Daniel
 */
public class Cita {

    private int idCita;
    private String Asunto;
    private Date Fecha;
    private Date Hora;
    private String FechaCreacion;
    private Dueno Dueno;
    private Veterinario Veterinario;
    private Usuario Usuario;

    public Cita() {
    }

    public Cita(int idCita, String Asunto, Date Fecha, Date Hora, String FechaCreacion, Dueno Dueno, Veterinario Veterinario, Usuario Usuario) {
        this.idCita = idCita;
        this.Asunto = Asunto;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.FechaCreacion = FechaCreacion;
        this.Dueno = Dueno;
        this.Veterinario = Veterinario;
        this.Usuario = Usuario;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date Hora) {
        this.Hora = Hora;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public Dueno getDueno() {
        return Dueno;
    }

    public void setDueno(Dueno Dueno) {
        this.Dueno = Dueno;
    }

    public Veterinario getVeterinario() {
        return Veterinario;
    }

    public void setVeterinario(Veterinario Veterinario) {
        this.Veterinario = Veterinario;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

}
