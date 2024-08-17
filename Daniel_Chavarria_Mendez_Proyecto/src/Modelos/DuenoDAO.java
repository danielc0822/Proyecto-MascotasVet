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
public class DuenoDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Dueno d = new Dueno();

    public List listarDueno() {

        List<Dueno> datos = new ArrayList<>();
        String sql = "SELECT * FROM dueno";
        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Dueno d = new Dueno();

                d.setNombre(rs.getString(1));
                d.setApellido1(rs.getString(2));
                d.setApellido2(rs.getString(3));
                d.setCedula(rs.getString(5));
                d.setGenero(rs.getString(6));
                d.setEmail(rs.getString(4));
                d.setTelefono(rs.getString(7));
                d.setDireccion(rs.getString(8));
                datos.add(d);

            }

        } catch (SQLException e) {

        }
        return datos;
    }

    public int agregarDueno(Dueno due) {

        int r = 0;
        String sql = "INSERT INTO dueno (Nombre, Apellido1, Apellido2, Cedula, Genero, Email, Telefono, Direccion) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, due.getNombre());
            ps.setString(2, due.getApellido1());
            ps.setString(3, due.getApellido2());
            ps.setString(4, due.getCedula());
            ps.setString(5, due.getGenero());
            ps.setString(6, due.getEmail());
            ps.setString(7, due.getTelefono());
            ps.setString(8, due.getDireccion());

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

    public int actualizarDueno(Dueno due) {

        int r = 0;
        String sql = "UPDATE dueno set Nombre=?, Apellido1=?, Apellido2=?, Cedula=?, Genero=?, Email=?, Telefono=?, Direccion=? WHERE IdDueno=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, due.getNombre());
            ps.setString(2, due.getApellido1());
            ps.setString(3, due.getApellido2());
            ps.setString(4, due.getCedula());
            ps.setString(5, due.getGenero());
            ps.setString(6, due.getEmail());
            ps.setString(7, due.getTelefono());
            ps.setString(8, due.getDireccion());
            ps.setInt(9, due.getIdDueno());
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

    public int eliminarDueno(int id) {
        int r = 0;
        String sql = "DELETE FROM dueno WHERE IdDueno=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {

        }

        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"IdDueno", "Nombre", "Apellido1", "Apellido2", "Cedula", "Genero", "Email", "Telefono", "Direccion"}; 
        String[] registros = new String[9];
        String sql = "SELECT * FROM dueno WHERE Nombre LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                registros[0] = rs.getString("IdDueno");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellido1");
                registros[3] = rs.getString("Apellido2");
                registros[4] = rs.getString("Cedula");
                registros[5] = rs.getString("Genero");
                registros[6] = rs.getString("Email");
                registros[7] = rs.getString("Telefono");
                registros[8] = rs.getString("Direccion");

                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

    public void cargarComboDueno(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("SELECCIONAR Dueño");
        String sql = "SELECT Nombre From dueno ";
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

    public void cargarIdDueno(JComboBox combo, JTextField texto) {

        String sql = "SELECT IdDueno From dueno WHERE Nombre = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("IdDueno")));

        } catch (SQLException e) {

        }
    }
    
    
    
    public void cargarComboCedula(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("SELECCIONAR Cedula");
        String sql = "SELECT Cedula From dueno ";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                combo.addItem(rs.getString("Cedula"));

            }

        } catch (SQLException e) {

        }
    }
          public void cargarCedula(JComboBox combo) {

        String sql = "SELECT Cedula From dueno WHERE Cedula = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();

        } catch (SQLException e) {

        }
    }
}
