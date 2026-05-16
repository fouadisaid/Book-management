package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnexionDB {

    private Connection cnx;
    private PreparedStatement pstm;
    private ResultSet rs;
    private int ok;

    // Connexion à PostgreSQL
    private void connect() {

        try {

            // Charger le driver PostgreSQL
            Class.forName(Constants.DRIVER);

            // Etablir la connexion
            cnx = DriverManager.getConnection(
                    Constants.URL,
                    Constants.USER,
                    Constants.PASSWORD
            );

            System.out.println("Connexion réussie !");

        } catch (Exception e) {

            System.out.println("Erreur de connexion à la BD : "
                    + e.getMessage());
        }
    }

    // Préparer une requête SQL
    public void initPrepar(String sql) {

        try {

            connect(); // se connecter à la base
            pstm = cnx.prepareStatement(sql);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // Exécuter SELECT
    public ResultSet executeSelect() {

        rs = null;

        try {

            rs = pstm.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rs;
    }

    // Exécuter : INSERT UPDATE DELETE
    public int executeMaj() {

        try {

            ok = pstm.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return ok;
    }

    // Fermer la connexion
    public void closeConnection() {

        try {

            if (cnx != null) {

                cnx.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {

        return pstm;
    }
}