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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class MascotaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Mascota m = new Mascota();

    public List listarMascota() {

        List<Mascota> datos = new ArrayList<>();
        String sql = "SELECT * FROM mascota";
        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Mascota m = new Mascota();

                m.setNombre(rs.getString(1));
                m.setGenero(rs.getString(2));
                m.setTipo(rs.getString(3));
                m.setRaza(rs.getString(4));
                m.setActivo(rs.getBoolean(5));
                m.setIdDueno(rs.getInt(6));

                datos.add(m);

            }

        } catch (SQLException e) {

        }
        return datos;
    }

    public int agregarMascota(Mascota Ma) {

        int r = 0;
        String sql = "INSERT INTO mascota (Nombre, Genero, Tipo, Raza, Activo, IdDueno) VALUES (?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Ma.getNombre());
            ps.setString(2, Ma.getGenero());
            ps.setString(3, Ma.getTipo());
            ps.setString(4, Ma.getRaza());
            ps.setBoolean(5, Ma.isActivo());
            ps.setInt(6, Ma.getIdDueno());
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

    public int actualizarMascota(Mascota Ma) {

        int r = 0;
        String sql = "UPDATE mascota set Nombre=?, Genero=?, Tipo=?, Raza=?, Activo=?, IdDueno=? WHERE IdMascota=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Ma.getNombre());
            ps.setString(2, Ma.getGenero());
            ps.setString(3, Ma.getTipo());
            ps.setString(4, Ma.getRaza());
            ps.setBoolean(5, Ma.isActivo());
            ps.setInt(6, Ma.getIdDueno());
            ps.setInt(7, Ma.getIdMascota());
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

    public int eliminarMascota(int id) {
        int r = 0;
        String sql = "DELETE FROM mascota WHERE IdMascota=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {

        }

        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"IdMascota", "Nombre", "Genero", "Tipo", "Raza", "Activo", "IdDueno"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM mascota WHERE Nombre LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                registros[0] = rs.getString("IdMascota");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Genero");
                registros[3] = rs.getString("Tipo");
                registros[4] = rs.getString("Raza");
                registros[5] = rs.getString("Activo");
                registros[6] = rs.getString("IdDueno");

                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

}
