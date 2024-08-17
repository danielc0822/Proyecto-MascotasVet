/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelos.CitaDAO;

import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import vista.frmCitasporFecha;

/**
 *
 * @author Daniel
 */
public class ControllerCitasFecha implements ActionListener {
    
    frmCitasporFecha vistaCitasFecha = new frmCitasporFecha();
    CitaDAO citaDAO = new CitaDAO();
    Helpers help = new Helpers();
        
    
    public ControllerCitasFecha(frmCitasporFecha frm) {
        this.vistaCitasFecha = frm;
        this.vistaCitasFecha.btBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCitasFecha.btBuscar) {
            filtrarTablaFecha(vistaCitasFecha.TableDatosCitasFecha, "Fecha", vistaCitasFecha.cbSeleccionarFecha.getSelectedItem().toString());
    }
    }
    public void filtrarTablaFecha(JTable table, String criterio, String filtro) {

        citaDAO.filtrarTablaCita(table, criterio, filtro);
        help.centrarCeldas(table);
    }

    public void iniciar() {
        cargarComboFecha();
       
        filtrarTablaFecha(vistaCitasFecha.TableDatosCitasFecha, "Fecha", "");
    }
    public void cargarComboFecha() {

        citaDAO.cargarComboFecha(vistaCitasFecha.cbSeleccionarFecha);

    }
}
