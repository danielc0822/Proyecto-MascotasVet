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
import Modelos.Veterinario;
import Modelos.VeterinarioDAO;

import vista.frmCatalogoVeterinario;

/**
 *
 * @author Daniel
 */
public class ControllerVeterinario implements ActionListener {

    VeterinarioDAO dao = new VeterinarioDAO();
    Veterinario v = new Veterinario();
    frmCatalogoVeterinario vistaVeterinario = new frmCatalogoVeterinario();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerVeterinario(frmCatalogoVeterinario frm) {

        this.vistaVeterinario = frm;
        this.vistaVeterinario.btGuardar.addActionListener(this);
        this.vistaVeterinario.btEditar.addActionListener(this);
        this.vistaVeterinario.btCancelar.addActionListener(this);
        this.vistaVeterinario.btRefrescar.addActionListener(this);
        this.vistaVeterinario.btEliminar.addActionListener(this);
        this.vistaVeterinario.btBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVeterinario.btGuardar) {
            if (vistaVeterinario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe ingresar un Nombre");
            } else if ((vistaVeterinario.txtCedula.getText().isEmpty())) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe ingresar su Cedula");
            } else {
                agregarVeterinario();

            }
        }
        if (e.getSource() == vistaVeterinario.btEditar) {
            if (vistaVeterinario.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe seleccionar un registro en la tabla para editar");
            } else if (vistaVeterinario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe ingresar un nombre");

            } else {
                actualizarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinario.btCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaVeterinario.btRefrescar) {
            filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, "");

        }
        if (e.getSource() == vistaVeterinario.btEliminar) {
            eliminarVeterinario();
        }
        if (e.getSource() == vistaVeterinario.btBuscar) {
            filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, vistaVeterinario.txtBuscar.getText());
        }
    }

    public void agregarVeterinario() {

        String nombre = vistaVeterinario.txtNombre.getText();
        String apellido1 = vistaVeterinario.txtApellido1.getText();
        String apellido2 = vistaVeterinario.txtApellido2.getText();
        String cedula = vistaVeterinario.txtCedula.getText();
        String codPro = vistaVeterinario.txtcodPro.getText();
        String email = vistaVeterinario.txtEmail.getText();
        String telefono = vistaVeterinario.txtTelefono.getText();
        String direccion = vistaVeterinario.txtDireccion.getText();
        boolean activo = vistaVeterinario.rbActivo.isSelected();

        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setCedula(cedula);
        v.setCodPro(codPro);
        v.setEmail(email);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        v.setActivo(activo);

        int r = dao.agregarVeterinario(v);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaVeterinario, "El Veterinario fue registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, "");
        } else {
            JOptionPane.showMessageDialog(vistaVeterinario, "El Veterinario no fue registrado");
        }

    }

    public void actualizarVeterinario() {

        int idVeterinario = Integer.parseInt(vistaVeterinario.txtID.getText());
        String nombre = vistaVeterinario.txtNombre.getText();
        String apellido1 = vistaVeterinario.txtApellido1.getText();
        String apellido2 = vistaVeterinario.txtApellido2.getText();
        String cedula = vistaVeterinario.txtCedula.getText();
        String codPro = vistaVeterinario.txtcodPro.getText();
        String email = vistaVeterinario.txtEmail.getText();
        String telefono = vistaVeterinario.txtTelefono.getText();
        String direccion = vistaVeterinario.txtDireccion.getText();
        boolean activo = vistaVeterinario.rbActivo.isSelected();
        v.setIdVeterinario(idVeterinario);
        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setCedula(cedula);
        v.setCodPro(codPro);
        v.setEmail(email);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        v.setActivo(activo);
        int r = dao.actualizarVeterinario(v);
        if (r == 1) {

            JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario actualizado con exito");
            filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, "");
            limpiarCampos();
            listarVeterinario(vistaVeterinario.TableDatosVete);
        } else {
            JOptionPane.showMessageDialog(vistaVeterinario, "El Veterinario no fue actualizado ");
        }

    }

    public void eliminarVeterinario() {
        int fila = vistaVeterinario.TableDatosVete.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaVeterinario.TableDatosVete, "Debe Seleccionar un registro en la tabla...");
        } else {
            int id = Integer.parseInt(vistaVeterinario.TableDatosVete.getValueAt(fila, 0).toString());
            dao.eliminarVeterinario(id);
            JOptionPane.showMessageDialog(vistaVeterinario.TableDatosVete, "Veterinario Eliminado con exito");
            filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, "");
            limpiarCampos();
        }
    }

    public void listarVeterinario(JTable tabla) {
        limpiarTabla(tabla, modelo);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);

        List<Veterinario> lista = dao.listarVeterinario();
        Object[] objeto = new Object[10];

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdVeterinario();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellido1();
            objeto[3] = lista.get(i).getApellido2();
            objeto[4] = lista.get(i).getCedula();
            objeto[5] = lista.get(i).getCodPro();
            objeto[6] = lista.get(i).getEmail();
            objeto[7] = lista.get(i).getTelefono();
            objeto[8] = lista.get(i).getDireccion();
            objeto[9] = lista.get(i).isActivo();

            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaVeterinario.txtID.setText("");
        vistaVeterinario.txtNombre.setText("");
        vistaVeterinario.txtApellido1.setText("");
        vistaVeterinario.txtApellido2.setText("");
        vistaVeterinario.txtCedula.setText("");
        vistaVeterinario.txtcodPro.setText("");
        vistaVeterinario.txtEmail.setText("");
        vistaVeterinario.txtTelefono.setText("");
        vistaVeterinario.txtDireccion.setText("");
        vistaVeterinario.rbActivo.setRolloverEnabled(true);

    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {

        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void iniciar() {
        filtrarTablaPorNombre(vistaVeterinario.TableDatosVete, vistaVeterinario.txtBuscar.getText());
        //listarUsuarios(vistaVeterinario.TableDatosUsuario);
        limpiarCampos();
    }
}
