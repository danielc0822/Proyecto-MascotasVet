/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class VeterinarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Veterinario v = new Veterinario();

    public List listarVeterinario() {

        List<Veterinario> datos = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";
        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Veterinario v = new Veterinario();

                v.setNombre(rs.getString(1));
                v.setApellido1(rs.getString(2));
                v.setApellido2(rs.getString(3));
                v.setCedula(rs.getString(4));
                v.setCodPro(rs.getString(5));
                v.setEmail(rs.getString(6));
                v.setTelefono(rs.getString(7));
                v.setDireccion(rs.getString(8));
                v.setActivo(rs.getBoolean(9));
                datos.add(v);

            }

        } catch (SQLException e) {

        }
        return datos;
    }

    public int agregarVeterinario(Veterinario vete) {

        int r = 0;
        String sql = "INSERT INTO veterinario (Nombre, Apellido1, Apellido2, Cedula, codProfecional, Email, Telefono, Direccion, Activo) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vete.getNombre());
            ps.setString(2, vete.getApellido1());
            ps.setString(3, vete.getApellido2());
            ps.setString(4, vete.getCedula());
            ps.setString(5, vete.getCodPro());
            ps.setString(6, vete.getEmail());
            ps.setString(7, vete.getTelefono());
            ps.setString(8, vete.getDireccion());
            ps.setBoolean(9, vete.isActivo());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {

                return 0;
            }

        } catch (SQLException e) {

        }

        return r;

    }

    public int actualizarVeterinario(Veterinario vete) {

        int r = 0;
        String sql = "UPDATE veterinario set Nombre=?, Apellido1=?, Apellido2=?, Cedula=?, codProfecional=?, Email=?, Telefono=?, Direccion=?, Activo=? WHERE IdVeterinario=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vete.getNombre());
            ps.setString(2, vete.getApellido1());
            ps.setString(3, vete.getApellido2());
            ps.setString(4, vete.getCedula());
            ps.setString(5, vete.getCodPro());
            ps.setString(6, vete.getEmail());
            ps.setString(7, vete.getTelefono());
            ps.setString(8, vete.getDireccion());
            ps.setBoolean(9, vete.isActivo());
            ps.setInt(10, vete.getIdVeterinario());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {

        }

        return r;
    }

    public int eliminarVeterinario(int id) {
        int r = 0;
        String sql = "DELETE FROM veterinario WHERE IdVeterinario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {

        }

        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"IdVeterinario", "Nombre", "Apellido1", "Apellido2", "Cedula", "codProfecional", "Email", "Telefono", "Direccion", "Activo"}; 
        String[] registros = new String[10];
        String sql = "SELECT * FROM veterinario WHERE Nombre LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
              
                registros[0] = rs.getString("IdVeterinario");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellido1");
                registros[3] = rs.getString("Apellido2");
                registros[4] = rs.getString("Cedula");
                registros[5] = rs.getString("codProfecional");
                registros[6] = rs.getString("Email");
                registros[7] = rs.getString("Telefono");
                registros[8] = rs.getString("Direccion");
                registros[9] = rs.getString("Activo");

                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

    public void cargarComboVeterinario(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("SELECCIONAR Veterinario");
        String sql = "SELECT Nombre From veterinario ";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                combo.addItem(rs.getString("Nombre"));

            }

        } catch (SQLException e) {

        }
    }

    public void cargarIdVeterinario(JComboBox combo, JTextField texto) {

        String sql = "SELECT IdVeterinario From veterinario WHERE Nombre = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("IdVeterinario")));

        } catch (SQLException e) {

        }
    }
}
