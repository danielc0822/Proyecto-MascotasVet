package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class Conexion {

    Connection ccn = null;
    Statement st = null;

    public Conexion() {
        try {
            String rutaFile;
            rutaFile = "./DB/Mi Mascota S.A.accdb";
            String url = "jdbc:ucanaccess://" + rutaFile;
            ccn = DriverManager.getConnection(url);
            st = ccn.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion" + e);
        }
    }

    public Connection getConnection() {

        return ccn;
    }

    public void desconexion() {

        try {

            ccn.close();
            System.exit(0);
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);

        }

    }

}
