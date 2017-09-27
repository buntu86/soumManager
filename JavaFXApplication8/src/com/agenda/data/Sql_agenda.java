package com.agenda.data;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
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
    private static ObservableList<Adresse> listeAdresses_byIdTypes = FXCollections.observableArrayList();
    
    public static ObservableList<Adresse> getAdresses() {
        update();
        return listeAdresses;
    }

    public static ObservableList<Adresse_type> getTypes() {
        update();
        return listeTypes;
    }    
    
    
    private static void connect() {
        try {
            Log.msg(0, "connexion");
            conn = DriverManager.getConnection("jdbc:sqlite:" + Config.getPath_agenda());
        } catch (SQLException e) {
            Log.msg(1, "Fail to create file " + Config.getPath_agenda() + " | "+ e.getMessage());
        } 
        Log.msg(0, "create table");
        String sql_createAdresses = "CREATE TABLE IF NOT EXISTS `Adresses` (\n"
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

        String sql_createTypes = "CREATE TABLE IF NOT EXISTS `Types` (\n"
                + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "`categorie` INTEGER"
                + ");";

        try {
            Statement stmt  = conn.createStatement();
            stmt.execute(sql_createAdresses);
            stmt.execute(sql_createTypes);
        } catch (SQLException e) {
            Log.msg(1, "Fail creation dbAgenda.db table | " + e.getMessage());
        }            
    }
    
    private static void update() {
        connect();
        listeAdresses.clear();
        listeTypes.clear();
        
        String sql_selectAdresses = "SELECT * FROM Adresses ORDER BY nom1 ASC";
        String sql_selectTypes = "SELECT * FROM Types ORDER BY categorie ASC";
        try{
            Statement stmt = conn.createStatement();
            Log.msg(0, "ResultSet sql: " + sql_selectAdresses);
            Log.msg(0, "ResultSet sql: " + sql_selectTypes);

            ResultSet rsTypes = stmt.executeQuery(sql_selectTypes);
            while(rsTypes.next()){
                listeTypes.add(new Adresse_type(
                    rsTypes.getInt("ID"),
                    rsTypes.getString("categorie")));
            }
            
            
            ResultSet rsAdresses = stmt.executeQuery(sql_selectAdresses);
            while(rsAdresses.next()){
                listeAdresses.add(new Adresse(
                    rsAdresses.getInt("ID"),
                    rsAdresses.getString("nom1"),
                    rsAdresses.getString("nom2"),
                    rsAdresses.getString("adresse1"),
                    rsAdresses.getString("adresse2"),
                    rsAdresses.getString("lieu"),
                    rsAdresses.getInt("npa"),
                    rsAdresses.getString("tel1"),
                    rsAdresses.getString("tel2"),
                    rsAdresses.getString("mail"),
                    rsAdresses.getInt("idType")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }       
    }    
    
    public static ObservableList<Adresse> getAdresses_byIdTypes(int id) {
        listeAdresses_byIdTypes.clear();
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Adresses WHERE idType=? ORDER BY nom1 ASC");
            pstmt.setInt(1, id);
            ResultSet rsAdresses = pstmt.executeQuery();
            while(rsAdresses.next()){
                listeAdresses_byIdTypes.add(new Adresse(
                    rsAdresses.getInt("ID"),
                    rsAdresses.getString("nom1"),
                    rsAdresses.getString("nom2"),
                    rsAdresses.getString("adresse1"),
                    rsAdresses.getString("adresse2"),
                    rsAdresses.getString("lieu"),
                    rsAdresses.getInt("npa"),
                    rsAdresses.getString("tel1"),
                    rsAdresses.getString("tel2"),
                    rsAdresses.getString("mail"),
                    rsAdresses.getInt("idType")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }       

        
        return listeAdresses_byIdTypes;
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

    public static Adresse getAdresseById(int id) {
        Adresse tmp = null;
        
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Adresses WHERE ID=?");
            pstmt.setInt(1, id);
            ResultSet rsAdresses = pstmt.executeQuery();
            while(rsAdresses.next()){
                tmp = new Adresse(
                    rsAdresses.getInt("ID"),
                    rsAdresses.getString("nom1"),
                    rsAdresses.getString("nom2"),
                    rsAdresses.getString("adresse1"),
                    rsAdresses.getString("adresse2"),
                    rsAdresses.getString("lieu"),
                    rsAdresses.getInt("npa"),
                    rsAdresses.getString("tel1"),
                    rsAdresses.getString("tel2"),
                    rsAdresses.getString("mail"),
                    rsAdresses.getInt("idType"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }     
        
        return tmp;
    }
}
