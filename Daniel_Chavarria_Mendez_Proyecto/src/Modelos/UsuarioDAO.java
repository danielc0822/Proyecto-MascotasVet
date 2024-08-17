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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Daniel
 */
public class UsuarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Usuario u = new Usuario();

    public List listarUsuarios() {

        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Usuario u = new Usuario();

                u.setNombre(rs.getString(1));
                u.setApellido1(rs.getString(2));
                u.setApellido2(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setNombreUsuario(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setTipoUsuario(rs.getString(7));
                u.setActivo(rs.getBoolean(8));
                datos.add(u);

            }

        } catch (SQLException e) {

        }
        return datos;
    }

    public int agregarUsuarios(Usuario Usu) {

        int r = 0;
        String sql = "INSERT INTO usuario (Nombre, Apellido1, Apellido2, Email, NombreUsuario, Password, TipoUsuario, Activo) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Usu.getNombre());
            ps.setString(2, Usu.getApellido1());
            ps.setString(3, Usu.getApellido2());
            ps.setString(4, Usu.getEmail());
            ps.setString(5, Usu.getNombreUsuario());
            ps.setString(6, Usu.getPassword());
            ps.setString(7, Usu.getTipoUsuario());
            ps.setBoolean(8, Usu.isActivo());
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

    public int actualizarUsuario(Usuario Usu) {

        int r = 0;
        String sql = "UPDATE usuario set Nombre=?, Apellido1=?, Apellido2=?, Email=?, NombreUsuario=?, Password=?, TipoUsuario=?, Activo=? WHERE IdUsuario=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Usu.getNombre());
            ps.setString(2, Usu.getApellido1());
            ps.setString(3, Usu.getApellido2());
            ps.setString(4, Usu.getEmail());
            ps.setString(5, Usu.getNombreUsuario());
            ps.setString(6, Usu.getPassword());
            ps.setString(7, Usu.getTipoUsuario());
            ps.setBoolean(8, Usu.isActivo());
            ps.setInt(9, Usu.getIdUsuario());
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

    public int eliminarUsuario(int id) {
        int r = 0;
        String sql = "DELETE FROM usuario WHERE IdUsuario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {

        }

        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"IdUsuario", "Nombre", "Apellido1", "Apellido2", "Email", "NombreUsuario", "Password", "TipoUsuario", "Activo"}; 
        String[] registros = new String[9];
        String sql = "SELECT * FROM usuario WHERE Nombre LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                registros[0] = rs.getString("IdUsuario");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellido1");
                registros[3] = rs.getString("Apellido2");
                registros[4] = rs.getString("Email");
                registros[5] = rs.getString("NombreUsuario");
                registros[6] = rs.getString("Password");
                registros[7] = rs.getString("TipoUsuario");
                registros[8] = rs.getString("Activo");

                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

   /* public void cargarComboUsuario(JComboBox combo) {

        combo.removeAllItems();
        combo.addItem("SELECCIONAR usuario");
        String sql = "SELECT Activo From usuario ";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                combo.addItem(rs.getString("Nombre"));

            }

        } catch (SQLException e) {

        }
    }*/

    public boolean login(Usuario user) {

        String sql = "SELECT IdUsuario, NombreUsuario, Password, TipoUsuario FROM usuario WHERE NombreUsuario=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombreUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {

                if (user.getPassword().equals(rs.getString(3)) && user.getTipoUsuario().equals(rs.getString(4))) {
                    user.setIdUsuario(rs.getInt(1));
                    user.setNombreUsuario(rs.getString(2));
                    user.setTipoUsuario(rs.getString(4));
                    return true;
                } else {
                    return false;
                }
            }

            return false;

        } catch (SQLException e) {
            return false;
        }
    }

    public int cargarIdUsuario(String nombreUsuario) {

        int idUsuario = 0;
        String sql = "SELECT IdUsuario FROM usuario WHERE NombreUsuario = '" + nombreUsuario + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            rs.next();
            idUsuario = rs.getInt("IdUsuario");
        } catch (SQLException e) {

        }

        return idUsuario;

    }
}
