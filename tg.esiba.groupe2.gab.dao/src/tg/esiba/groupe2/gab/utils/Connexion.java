/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fad
 */
public class Connexion {
    /**
     * adresse du serveur
     */
    public static String server = "localhost";

    /**
     * le nom de la base de donnée
     */
    public static String dbname = "db_gab";

    /**
     * utilisateur de la base de donnée
     */
    public static String user = "root";

    /**
     * mot de passe de l'utilisateur de la base de donnée
     */
    public static String password = "";

    /**
     * etat de la connection
     */
    public static Connection con = null;

    /**
     * classe permettant de se conecter
     *
     * @return con, l'etat de la connection
     */
    public static Connection getConnexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement de pilote" + ex);
        }

        String url = "jdbc:mysql://" + server + "/" + dbname;

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Erreur de connexion à la base  de donnée" + ex);
        }
        return con;
    }

    /**
     * methode de connexion
     * @return con.createStatement() , cré un statement
     * @throws SQLException , renvoie une exception de type SQLException
     */
    public Statement getStatement() throws SQLException {
        con = getConnexion();
        return con.createStatement();
    }
}
