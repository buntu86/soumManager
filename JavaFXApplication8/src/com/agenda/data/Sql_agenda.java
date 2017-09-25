package com.agenda.data;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sql_agenda {
    private static Connection conn = null;
    private static ObservableList<Adresse> listeAdresses = FXCollections.observableArrayList();
    private static ObservableList<Adresse_type> listeTypes = FXCollections.observableArrayList();
    
    public static ObservableList<Adresse> getAdresses() {
        update();
        return listeAdresses;
    }
    
    private static void connect() {
        try {
            Log.msg(0, "connexion");
            conn = DriverManager.getConnection("jdbc:sqlite:" + Config.getPath_agenda());
        } catch (SQLException e) {
            Log.msg(1, "Fail to create file " + Config.getPath_agenda() + " | "+ e.getMessage());
        } 
        Log.msg(0, "create table");
        String sql = "CREATE TABLE IF NOT EXISTS `Adresses` (\n"
                + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "`idType` INTEGER,\n"
                + "`nom1` TEXT,\n"
                + "`nom2` TEXT,\n"
                + "`tel1` TEXT,\n"
                + "`tel2` TEXT,\n"
                + "`mail` TEXT,\n"
                + "`adresse1` TEXT,\n"
                + "`adresse2` TEXT,\n"
                + "`npa` INTEGER,\n"
                + "`lieu` TEXT\n"
                + ");";
        try {
            Statement stmt  = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            Log.msg(1, "Fail creation dbAgenda.db table | " + e.getMessage());
        }            
    }
    
    private static void update() {
        connect();
        listeAdresses.clear();
        
        String sql = "SELECT * FROM Adresses ORDER BY nom1 ASC";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Log.msg(0, "ResultSet sql: " + sql);
            while(rs.next()){
                listeAdresses.add(new Adresse(
                    rs.getInt("ID"),
                    rs.getString("nom1"),
                    rs.getString("nom2"),
                    rs.getString("adresse1"),
                    rs.getString("adresse2"),
                    rs.getString("lieu"),
                    rs.getInt("npa"),
                    rs.getString("tel1"),
                    rs.getString("tel2"),
                    rs.getString("mail"),
                    rs.getInt("idType")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }       
    }    
    
    public static void add(Adresse adresse) {
        connect();
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Adresses ("
                + "idType,"
                + "nom1,"
                + "nom2,"
                + "tel1,"
                + "tel2,"
                + "mail,"
                + "adresse1,"
                + "adresse2,"
                + "npa,"
                + "lieu) VALUES("
                    + "?, " //idType 1
                    + "?, " //nom1 2
                    + "?, " //nom2 3
                    + "?, " //tel1 4
                    + "?, " //tel2 5
                    + "?, " //mail 6
                    + "?, " //adresse1 7
                    + "?, " //adresse2 8
                    + "?, " //npa 9
                    + "?)"); //lieu 10
            pstmt.setInt(1, adresse.getIdAdresseType());
            pstmt.setString(2, adresse.getNom1());
            pstmt.setString(3, adresse.getNom2());
            pstmt.setString(4, adresse.getTel1());
            pstmt.setString(5, adresse.getTel2());
            pstmt.setString(6, adresse.getMail());
            pstmt.setString(7, adresse.getAdresse1());
            pstmt.setString(8, adresse.getAdresse2());
            pstmt.setInt(9, adresse.getNpa());
            pstmt.setString(10, adresse.getLieu());
            pstmt.executeUpdate();
            pstmt.close();              
        }
        catch(SQLException e){        
            Log.msg(1, "Fail add adresse sql " + e);
        }
    }

    public static ObservableList<Adresse_type> getTypes() {
        return listeTypes;
    }
}
