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
import Modelos.Usuario;
import Modelos.UsuarioDAO;
import vista.frmCatalogoUsuario;

/**
 *
 * @author Daniel
 */
public class ControllerUsuario implements ActionListener {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = new Usuario();
    frmCatalogoUsuario vistaUsuario = new frmCatalogoUsuario();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerUsuario(frmCatalogoUsuario frm) {

        this.vistaUsuario = frm;
        this.vistaUsuario.btGuardar.addActionListener(this);
        this.vistaUsuario.btEditar.addActionListener(this);
        this.vistaUsuario.btCancelar.addActionListener(this);
        this.vistaUsuario.btRefrescar.addActionListener(this);
        this.vistaUsuario.btEliminar.addActionListener(this);
        this.vistaUsuario.btBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaUsuario.btGuardar) {
            if (vistaUsuario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un nombre");
            } else if (!vistaUsuario.txtPassword.getText().equals(vistaUsuario.txtConfirmar.getText())) {
                JOptionPane.showMessageDialog(vistaUsuario, "Contraseña no conciden");
            } else {
                agregarUsuario();

            }
        }
        if (e.getSource() == vistaUsuario.btEditar) {
            if (vistaUsuario.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un registro en la tabla para editar");
            } else if (vistaUsuario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un nombre");
            } else if (vistaUsuario.txtNombreUsu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe ingresar un nombre de usuario");
            } else if (!vistaUsuario.txtPassword.getText().equals(vistaUsuario.txtConfirmar.getText())) {
                JOptionPane.showMessageDialog(vistaUsuario, "Contraseñas no coinciden");
            } else {
                actualizarUsuario();
            }
        }
        if (e.getSource() == vistaUsuario.btCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaUsuario.btRefrescar) {
            filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, "");

        }
        if (e.getSource() == vistaUsuario.btEliminar) {
            eliminarUsuario();
        }
        if (e.getSource() == vistaUsuario.btBuscar) {
            filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, vistaUsuario.txtBuscar.getText());
        }
    }

    public void agregarUsuario() {

        String nombre = vistaUsuario.txtNombre.getText();
        String apellido1 = vistaUsuario.txtApellido1.getText();
        String apellido2 = vistaUsuario.txtApellido2.getText();
        String email = vistaUsuario.txtEmail.getText();
        String nombreUsuario = vistaUsuario.txtNombreUsu.getText();
        String password = vistaUsuario.txtPassword.getText();
        String tipoUsuario = vistaUsuario.cbTipodeUsu.getSelectedItem().toString();
        boolean activo = vistaUsuario.rbActivo.isSelected();

        u.setNombre(nombre);
        u.setApellido1(apellido1);
        u.setApellido2(apellido2);
        u.setEmail(email);
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(password);
        u.setTipoUsuario(tipoUsuario);
        u.setActivo(activo);

        int r = dao.agregarUsuarios(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, "");
        } else {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario no fue registrado");
        }

    }

    public void actualizarUsuario() {

        int idUsuario = Integer.parseInt(vistaUsuario.txtID.getText());
        String nombre = vistaUsuario.txtNombre.getText();
        String apellido1 = vistaUsuario.txtApellido1.getText();
        String apellido2 = vistaUsuario.txtApellido2.getText();
        String email = vistaUsuario.txtEmail.getText();
        String nombreUsuario = vistaUsuario.txtNombreUsu.getText();
        String password = vistaUsuario.txtPassword.getText();
        String tipoUsuario = vistaUsuario.cbTipodeUsu.getSelectedItem().toString();
        boolean activo = vistaUsuario.rbActivo.isSelected();
        u.setIdUsuario(idUsuario);
        u.setNombre(nombre);
        u.setApellido1(apellido1);
        u.setApellido2(apellido2);
        u.setEmail(email);
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(password);
        u.setTipoUsuario(tipoUsuario);
        u.setActivo(activo);
        int r = dao.actualizarUsuario(u);
        if (r == 1) {

            JOptionPane.showMessageDialog(vistaUsuario, "Usuario actualizado con exito");
            filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, "");
            limpiarCampos();
            listarUsuarios(vistaUsuario.TableDatosUsuario);
        } else {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario no fue actualizado ");
        }

    }

    public void eliminarUsuario() {
        int fila = vistaUsuario.TableDatosUsuario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuario.TableDatosUsuario, "Debe Seleccionar un registro en la tabla...");
        } else {
            int id = Integer.parseInt(vistaUsuario.TableDatosUsuario.getValueAt(fila, 0).toString());
            dao.eliminarUsuario(id);
            JOptionPane.showMessageDialog(vistaUsuario.TableDatosUsuario, "Usuario Eliminado con exito");
            filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, "");
            limpiarCampos();
        }
    }

    public void listarUsuarios(JTable tabla) {
        limpiarTabla(tabla, modelo);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);

        List<Usuario> lista = dao.listarUsuarios();
        Object[] objeto = new Object[9];

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdUsuario();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellido1();
            objeto[3] = lista.get(i).getApellido2();
            objeto[4] = lista.get(i).getEmail();
            objeto[5] = lista.get(i).getNombreUsuario();
            objeto[6] = lista.get(i).getPassword();
            objeto[7] = lista.get(i).getTipoUsuario();
            objeto[8] = lista.get(i).isActivo();

            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaUsuario.txtID.setText("");
        vistaUsuario.txtNombre.setText("");
        vistaUsuario.txtApellido1.setText("");
        vistaUsuario.txtApellido2.setText("");
        vistaUsuario.txtEmail.setText("");
        vistaUsuario.txtNombreUsu.setText("");
        vistaUsuario.txtPassword.setText("");
        vistaUsuario.txtConfirmar.setText("");
        vistaUsuario.cbTipodeUsu.setSelectedIndex(0);
        vistaUsuario.rbActivo.setRolloverEnabled(true);

    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {

        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void iniciar() {
        filtrarTablaPorNombre(vistaUsuario.TableDatosUsuario, vistaUsuario.txtBuscar.getText());
        //listarUsuarios(vistaUsuario.TableDatosUsuario);
        limpiarCampos();
    }
}
