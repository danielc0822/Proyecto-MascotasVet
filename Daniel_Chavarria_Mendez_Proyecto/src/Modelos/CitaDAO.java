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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class CitaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Cita c = new Cita();

    public int agregarCita(Cita ci) {
        int r = 0;
        String sql = "INSERT INTO cita (Asunto, Fecha, Hora, FechaCreacion, IdDueno, IdUsuario, IdVeterinario) VALUES (?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ci.getAsunto());
            ps.setDate(2, ci.getFecha());
            ps.setDate(3, ci.getHora());
            ps.setString(4, ci.getFechaCreacion());
            ps.setInt(5, ci.getDueno().getIdDueno());
            ps.setInt(6, ci.getUsuario().getIdUsuario());
            ps.setInt(7, ci.getVeterinario().getIdVeterinario());
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

    public int eliminarCita(int idCita) {
        int r = 0;
        String sql = "DELETE FROM cita WHERE IdCita=" + idCita;

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {

        }
        return r;
    }

    public void filtrarTablaCita(JTable table, String criterio, String filtro) {
        String[] titulos = {"IdCita", "Asunto", "FechaCreacion", "IdDueno", "IdUsuario", "IdVeterinario "};
        String[] registros = new String[6];
        String sql = "SELECT cita.IdCita, cita.Asunto, cita.FechaCreacion, dueno.IdDueno, usuario.IdUsuario, veterinario.IdVeterinario "
                + "FROM (((cita "
                + "INNER JOIN dueno ON cita.IdDueno = dueno.IdDueno)"
                + " INNER JOIN usuario ON cita.IdUsuario = usuario.IdUsuario)"
                + " INNER JOIN veterinario ON cita.IdVeterinario = veterinario.IdVeterinario) WHERE " + criterio + " LIKE'%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel(null, titulos);

        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("Asunto");
                registros[2] = rs.getString("FechaCreacion");
                registros[3] = rs.getString("IdDueno");
                registros[4] = rs.getString("IdUsuario");
                registros[5] = rs.getString("IdVeterinario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {

        }
    }
    
      public void cargarComboFecha(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("SELECCIONAR Fecha");
        String sql = "SELECT Fecha From cita ";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                combo.addItem(rs.getString("Fecha"));

            }

        } catch (SQLException e) {

        }
    }
          public void cargarFecha(JComboBox combo) {

        String sql = "SELECT Fecha From cita WHERE Fecha = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();

        } catch (SQLException e) {

        }
    }

}
