/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import Modelos.Cita;
import Modelos.CitaDAO;
import Modelos.Dueno;
import Modelos.DuenoDAO;
import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Modelos.Veterinario;
import Modelos.VeterinarioDAO;
import javax.swing.JTable;
import vista.frmCatalogoCita;
import vista.frmVentanaPrincipal;

/**
 *
 * @author Daniel
 */
public class ControllerCita implements ActionListener {

    Cita cita = new Cita();
    CitaDAO citaDAO = new CitaDAO();
    Usuario usuario = new Usuario();
    Dueno dueno = new Dueno();
    Veterinario veterinario = new Veterinario();

    Helpers help = new Helpers();

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    DuenoDAO duenoDAO = new DuenoDAO();
    VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

    frmCatalogoCita vistaCita = new frmCatalogoCita();

    public ControllerCita(frmCatalogoCita frm) {
        this.vistaCita = frm;
        this.vistaCita.btGuardar.addActionListener(this);
        this.vistaCita.btEliminar.addActionListener(this);
        this.vistaCita.btRefrescar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCita.btGuardar) {

            if (vistaCita.cbSeleccionarVete.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCita, "Debe Seleccionar un Veterinario");
            } else if (vistaCita.cbSeleccionarNombre.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCita, "Debe Seleccionar un Dueño");
            } else {
                try {
                    agregarCita();
                } catch (ParseException ex) {

                }
            }
        }
        if (e.getSource() == vistaCita.btEliminar) {
            eliminarCita();

        }
        if (e.getSource() == vistaCita.btRefrescar) {
            filtrarTablaCita(vistaCita.TableDatosCitas, "IdUsuario", "");
        }
    }

    public void agregarCita() throws ParseException {

        Integer idUsuario = Integer.valueOf(frmVentanaPrincipal.lblIdUsuario.getText());
        int idDueno = Integer.valueOf(vistaCita.txtIdDueno.getText());
        int idVeterinario = Integer.valueOf(vistaCita.txtIdVeterinario.getText());
        String asunto = vistaCita.txtAsunto.getText();
        String fechaCreacion = vistaCita.txtFechadeCreacion.getText();
        veterinario.setIdVeterinario(idVeterinario);
        dueno.setIdDueno(idDueno);
        usuario.setIdUsuario(idUsuario);
        cita.setVeterinario(veterinario);
        cita.setDueno(dueno);
        cita.setUsuario(usuario);
        cita.setFecha(help.fechaActual());
        cita.setHora(help.horaActual());
        cita.setAsunto(asunto);
        cita.setFechaCreacion(fechaCreacion);

        int r = citaDAO.agregarCita(cita);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaCita, "Cita agregada con exito");
            filtrarTablaCita(vistaCita.TableDatosCitas, "IdUsuario", "");
            limpiarCampos();
        }
    }

    public void cargarComboDuenos() {

        duenoDAO.cargarComboDueno(vistaCita.cbSeleccionarNombre);

    }

    public void cargarComboVeterinarios() {

        veterinarioDAO.cargarComboVeterinario(vistaCita.cbSeleccionarVete);

    }

    public void eliminarCita() {

        int fila = vistaCita.TableDatosCitas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(vistaCita, "Debe seleccionar un registro");

        } else {

            int idCitas = Integer.parseInt((String) vistaCita.TableDatosCitas.getValueAt(fila, 0));
            int r = citaDAO.eliminarCita(idCitas);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaCita, "Cita anulada");
                filtrarTablaCita(vistaCita.TableDatosCitas, "IdUsuario", "");

            }

        }
    }

    public void filtrarTablaCita(JTable table, String criterio, String filtro) {

        citaDAO.filtrarTablaCita(table, criterio, filtro);
        help.centrarCeldas(table);
    }

    public void limpiarCampos() {
        vistaCita.txtAsunto.setText("");
        vistaCita.txtFechadeCreacion.setText("");
        vistaCita.txtIdCita.setText("");
        vistaCita.txtIdDueno.setText("");
        vistaCita.txtIdUsuario.setText("");
        vistaCita.txtIdVeterinario.setText("");

        cargarComboDuenos();
        cargarComboVeterinarios();
    }

    public void iniciar() {

        limpiarCampos();
        vistaCita.txtIdUsuario.setText(frmVentanaPrincipal.lblIdUsuario.getText());
        filtrarTablaCita(vistaCita.TableDatosCitas, "IdCita", "");

    }
}
