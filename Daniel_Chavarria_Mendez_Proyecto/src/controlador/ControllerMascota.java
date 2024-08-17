/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelos.Dueno;
import Modelos.DuenoDAO;
import Modelos.Mascota;
import Modelos.MascotaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmCatalogoMascota;

/**
 *
 * @author Daniel
 */
public class ControllerMascota implements ActionListener {

    MascotaDAO dao = new MascotaDAO();
    Mascota m = new Mascota();
    Dueno dueno = new Dueno();
    DuenoDAO duenoDAO = new DuenoDAO();

    frmCatalogoMascota vistaMascota = new frmCatalogoMascota();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerMascota(frmCatalogoMascota frm) {

        this.vistaMascota = frm;
        this.vistaMascota.btGuardar.addActionListener(this);
        this.vistaMascota.btEditar.addActionListener(this);
        this.vistaMascota.btCancelar.addActionListener(this);
        this.vistaMascota.btRefrescar.addActionListener(this);
        this.vistaMascota.btEliminar.addActionListener(this);
        this.vistaMascota.btBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMascota.btGuardar) {
            if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else if (vistaMascota.txtIdDueno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else {
                agregarMascota();

            }
        }
        if (e.getSource() == vistaMascota.btEditar) {
            if (vistaMascota.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un registro en la tabla para editar");
            } else if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else {
                actualizarMascota();
            }
        }
        if (e.getSource() == vistaMascota.btCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaMascota.btRefrescar) {
            filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, "");

        }
        if (e.getSource() == vistaMascota.btEliminar) {
            eliminarMascota();
        }
        if (e.getSource() == vistaMascota.btBuscar) {
            filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, vistaMascota.txtBuscar.getText());
        }
    }

    public void agregarMascota() {

        String nombre = vistaMascota.txtNombre.getText();
        String genero = vistaMascota.cbGenero.getSelectedItem().toString();
        String tipo = vistaMascota.txtTipo.getText();
        String raza = vistaMascota.txtRaza.getText();
        boolean activo = vistaMascota.rbActivo.isSelected();
        int idDueno = Integer.parseInt(vistaMascota.txtIdDueno.getText());

        m.setNombre(nombre);
        m.setGenero(genero);
        m.setTipo(tipo);
        m.setRaza(raza);
        m.setActivo(activo);
        m.setIdDueno(idDueno);

        int r = dao.agregarMascota(m);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, "");
        } else {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota no fue registrado");
        }

    }

    public void actualizarMascota() {

        int idMascota = Integer.parseInt(vistaMascota.txtID.getText());
        String nombre = vistaMascota.txtNombre.getText();
        String genero = vistaMascota.cbGenero.getSelectedItem().toString();
        String tipo = vistaMascota.txtTipo.getText();
        String raza = vistaMascota.txtRaza.getText();
        boolean activo = vistaMascota.rbActivo.isSelected();
        int idDueno = Integer.parseInt(vistaMascota.txtIdDueno.getText());
        m.setIdMascota(idMascota);
        m.setNombre(nombre);
        m.setGenero(genero);
        m.setTipo(tipo);
        m.setRaza(raza);
        m.setActivo(activo);
        m.setIdDueno(idDueno);
        int r = dao.actualizarMascota(m);
        if (r == 1) {

            JOptionPane.showMessageDialog(vistaMascota, "Mascota actualizado con exito");
            filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaMascota, "La Mascota no fue actualizado ");
        }

    }

    public void eliminarMascota() {
        int fila = vistaMascota.TableDatosMascotas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMascota.TableDatosMascotas, "Debe Seleccionar un registro en la tabla...");
        } else {
            int id = Integer.parseInt(vistaMascota.TableDatosMascotas.getValueAt(fila, 0).toString());
            dao.eliminarMascota(id);
            JOptionPane.showMessageDialog(vistaMascota.TableDatosMascotas, "Mascota Eliminada con exito");
            filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, "");
            limpiarCampos();
        }
    }

    public void cargarComboDuenos() {

        duenoDAO.cargarComboDueno(vistaMascota.cbSeleccionarNombre);

    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaMascota.txtID.setText("");
        vistaMascota.txtNombre.setText("");
        vistaMascota.txtRaza.setText("");
        vistaMascota.txtTipo.setText("");
        vistaMascota.txtIdDueno.setText("");
        vistaMascota.cbSeleccionarNombre.setSelectedIndex(0);
        vistaMascota.cbGenero.setSelectedIndex(0);
        vistaMascota.rbActivo.setRolloverEnabled(true);
        cargarComboDuenos();

    }

    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaMascota.TableDatosMascotas, "");
    }
}
