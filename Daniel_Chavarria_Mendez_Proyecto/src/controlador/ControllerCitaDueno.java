/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelos.CitaDAO;
import Modelos.DuenoDAO;
import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import vista.frmCitasporDueno;

/**
 *
 * @author Daniel
 */
public class ControllerCitaDueno implements ActionListener {
    
    frmCitasporDueno vistaCitasDueno = new frmCitasporDueno();
    DuenoDAO duenoDAO = new DuenoDAO();
    CitaDAO citaDAO = new CitaDAO();
    Helpers help = new Helpers();
        
    
    public ControllerCitaDueno(frmCitasporDueno frm) {
        this.vistaCitasDueno = frm;
        this.vistaCitasDueno.btBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCitasDueno.btBuscar) {
            filtrarTablaDueno(vistaCitasDueno.TableDatosCitasDueno, "Cedula", vistaCitasDueno.cbSeleccionarCedula.getSelectedItem().toString());
    }
    }
    public void filtrarTablaDueno(JTable table, String criterio, String filtro) {

        citaDAO.filtrarTablaCita(table, criterio, filtro);
        help.centrarCeldas(table);
    }

    public void iniciar() {
        
        cargarComboDuenos();
       
        filtrarTablaDueno(vistaCitasDueno.TableDatosCitasDueno, "Cedula", "");
    }
    public void cargarComboDuenos() {

        duenoDAO.cargarComboCedula(vistaCitasDueno.cbSeleccionarCedula);

    }


}
