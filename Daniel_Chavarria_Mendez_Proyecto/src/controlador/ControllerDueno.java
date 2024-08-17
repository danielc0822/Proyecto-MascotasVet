/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelos.Dueno;
import Modelos.DuenoDAO;
import vista.frmCatalogoDueno;

/**
 *
 * @author Daniel
 */
public class ControllerDueno implements ActionListener {

    DuenoDAO dao = new DuenoDAO();
    Dueno d = new Dueno();
    frmCatalogoDueno vistaDueno = new frmCatalogoDueno();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerDueno(frmCatalogoDueno frm) {

        this.vistaDueno = frm;
        this.vistaDueno.btGuardar.addActionListener(this);
        this.vistaDueno.btEditar.addActionListener(this);
        this.vistaDueno.btCancelar.addActionListener(this);
        this.vistaDueno.btRefrescar.addActionListener(this);
        this.vistaDueno.btEliminar.addActionListener(this);
        this.vistaDueno.btBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDueno.btGuardar) {
            if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un Nombre");
            } else if ((vistaDueno.txtCedula.getText().isEmpty())) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar su Cedula");
            } else {
                agregarDueno();

            }
        }
        if (e.getSource() == vistaDueno.btEditar) {
            if (vistaDueno.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe seleccionar un registro en la tabla para editar");
            } else if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un nombre");

            } else {
                actualizarDueno();
            }
        }
        if (e.getSource() == vistaDueno.btCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaDueno.btRefrescar) {
            filtrarTablaPorNombre(vistaDueno.TableDatosDueno, "");

        }
        if (e.getSource() == vistaDueno.btEliminar) {
            eliminarDueno();
        }
        if (e.getSource() == vistaDueno.btBuscar) {
            filtrarTablaPorNombre(vistaDueno.TableDatosDueno, vistaDueno.txtBuscar.getText());
        }
    }

    public void agregarDueno() {

        String nombre = vistaDueno.txtNombre.getText();
        String apellido1 = vistaDueno.txtApellido1.getText();
        String apellido2 = vistaDueno.txtApellido2.getText();
        String cedula = vistaDueno.txtCedula.getText();
        String genero = vistaDueno.cbGenero.getSelectedItem().toString();
        String email = vistaDueno.txtEmail.getText();
        String telefono = vistaDueno.txtTelefono.getText();
        String direccion = vistaDueno.txtDireccion.getText();

        d.setNombre(nombre);
        d.setApellido1(apellido1);
        d.setApellido2(apellido2);
        d.setCedula(cedula);
        d.setGenero(genero);
        d.setEmail(email);
        d.setTelefono(telefono);
        d.setDireccion(direccion);

        int r = dao.agregarDueno(d);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaDueno.TableDatosDueno, "");
            listarDuenos(vistaDueno.TableDatosDueno);
        } else {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño no fue registrado");
        }

    }

    public void actualizarDueno() {

        int idDueno = Integer.parseInt(vistaDueno.txtID.getText());
        String nombre = vistaDueno.txtNombre.getText();
        String apellido1 = vistaDueno.txtApellido1.getText();
        String apellido2 = vistaDueno.txtApellido2.getText();
        String cedula = vistaDueno.txtCedula.getText();
        String genero = vistaDueno.cbGenero.getSelectedItem().toString();
        String email = vistaDueno.txtEmail.getText();
        String telefono = vistaDueno.txtTelefono.getText();
        String direccion = vistaDueno.txtDireccion.getText();
        d.setIdDueno(idDueno);
        d.setNombre(nombre);
        d.setApellido1(apellido1);
        d.setApellido2(apellido2);
        d.setCedula(cedula);
        d.setGenero(genero);
        d.setEmail(email);
        d.setTelefono(telefono);
        d.setDireccion(direccion);
        int r = dao.actualizarDueno(d);
        if (r == 1) {

            JOptionPane.showMessageDialog(vistaDueno, "Dueño actualizado con exito");
            filtrarTablaPorNombre(vistaDueno.TableDatosDueno, "");
            limpiarCampos();
            listarDuenos(vistaDueno.TableDatosDueno);
        } else {
            JOptionPane.showMessageDialog(vistaDueno, "El Dueño no fue actualizado ");
        }

    }

    public void eliminarDueno() {
        int fila = vistaDueno.TableDatosDueno.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDueno.TableDatosDueno, "Debe Seleccionar un registro en la tabla...");
        } else {
            int id = Integer.parseInt(vistaDueno.TableDatosDueno.getValueAt(fila, 0).toString());
            dao.eliminarDueno(id);
            JOptionPane.showMessageDialog(vistaDueno.TableDatosDueno, "Dueño Eliminado con exito");
            filtrarTablaPorNombre(vistaDueno.TableDatosDueno, "");
            limpiarCampos();
        }
    }

    public void listarDuenos(JTable tabla) {
        limpiarTabla(tabla, modelo);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);

        List<Dueno> lista = dao.listarDueno();
        Object[] objeto = new Object[9];

        for (int i = 0; i < lista.size(); i++) {

            objeto[0] = lista.get(i).getIdDueno();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellido1();
            objeto[3] = lista.get(i).getApellido2();
            objeto[4] = lista.get(i).getCedula();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getEmail();
            objeto[7] = lista.get(i).getTelefono();
            objeto[8] = lista.get(i).getDireccion();

            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaDueno.txtID.setText("");
        vistaDueno.txtNombre.setText("");
        vistaDueno.txtApellido1.setText("");
        vistaDueno.txtApellido2.setText("");
        vistaDueno.txtCedula.setText("");
        vistaDueno.cbGenero.setSelectedIndex(0);
        vistaDueno.txtEmail.setText("");
        vistaDueno.txtTelefono.setText("");
        vistaDueno.txtDireccion.setText("");

    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {

        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void iniciar() {
        filtrarTablaPorNombre(vistaDueno.TableDatosDueno, vistaDueno.txtBuscar.getText());

        limpiarCampos();
    }
}
