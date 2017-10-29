package com.soumManager.data;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.soumManager.model.ListePrix;
import com.soumManager.utils.Log;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Sql_Projet {
    private static Connection conn = null;
    private static Path pathProjet = null;
    
    public static void createProjet(String numProjet, String nomProjet, String pathProjet){
        Sql_Projet.pathProjet = Paths.get(pathProjet);
        connect();
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Header ("
                + "numMandat,"
                + "nomMandat) VALUES("
                    + "?, " //num 1
                    + "?)"); //nom 2
            pstmt.setString(1, numProjet.trim());
            pstmt.setString(2, nomProjet.trim());
            pstmt.executeUpdate();
            pstmt.close();              
        }
        catch(SQLException e){        
            Log.msg(1, "Fail create projet sql " + e);
        }                
    }
    
    private static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + pathProjet);
            Log.msg(0, "create table");            

            String sql_header = "CREATE TABLE IF NOT EXISTS `Header` (\n"
                    + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "`numMandat` TEXT,\n"
                    + "`nomMandat` TEXT,\n"
                    + "`idListePrixAdj` INTEGER,\n"
                    + "`dateAdj` INTEGER"
                    + ");";            
            String sql_entreprises = "CREATE TABLE IF NOT EXISTS `ListeEntreprises` (\n"
                    + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "`idEntreprise` INTEGER,\n"
                    + "`nomParDefaut` TEXT\n"
                    + ");";
            String sql_listesPrix = "CREATE TABLE IF NOT EXISTS `Listes_prix` (\n"
                    + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "`idEntreprise` INTEGER,\n"
                    + "`titre` TEXT,\n"
                    + "`date` INTEGER,\n"
                    + "`rabais1` INTEGER,\n"
                    + "`rabais2` INTEGER,\n"
                    + "`escompte` INTEGER,\n"
                    + "`remarques` TEXT\n"
                    + ");";            
            String sql_postes = "CREATE TABLE IF NOT EXISTS `Postes` (\n"
                    + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "`idListePrix` INTEGER,\n"
                    + "`hs` INTEGER,\n"
                    + "`idChapitre` INTEGER,\n"
                    + "`num` INTEGER,\n"
                    + "`nom` TEXT,\n"
                    + "`unite` TEXT,\n"
                    + "`quantite` INTEGER,\n"
                    + "`prixUnit` INTEGER\n"
                    + ");";   
            try {
                Statement stmt  = conn.createStatement();
                stmt.execute(sql_header);
                stmt.execute(sql_entreprises);
                stmt.execute(sql_listesPrix);
                stmt.execute(sql_postes);
            } catch (SQLException e) {
                Log.msg(1, "Fail creation " + pathProjet.toString() + " table | " + e.getMessage());
            }               
        } catch (SQLException e) {
            Log.msg(1, "Fail to create file " + pathProjet.toString() + " | "+ e.getMessage());
        }
    }

    public static String getNumProjet() {
        connect();
        String tmp = "";
        
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Header";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                tmp = rs.getString("numMandat");
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }   
        return tmp;
    }

    public static String getNomProjet() {
        connect();        
        String tmp = "";

        try{
            String sql_nomProjet = "SELECT * FROM Header";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_nomProjet);
            while(rs.next())
                tmp = rs.getString("nomMandat");       
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }   
        return tmp;
    }

    public static ObservableList<ListePrix> getListePrix() {
        connect();
        ObservableList<ListePrix> liste = FXCollections.observableArrayList();;

        try{
            String sql = "SELECT * FROM Listes_Prix";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                liste.add(new ListePrix(
                        rs.getString("titre"),
                        rs.getInt("idEntreprise"),
                        rs.getInt("rabais1"),
                        rs.getInt("rabais2"),
                        rs.getInt("escompte"),
                        rs.getString("remarques"),
                        rs.getInt("date")));                        
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }   
        return liste;        
    }

    public static void setPath(String pathProjet) {
        Sql_Projet.pathProjet = Paths.get(pathProjet);
    }
    
    public static void addListePrix(ListePrix lp_tmp){
        connect();
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Listes_prix ("
                    + "idEntreprise, "
                    + "titre, "
                    + "date, "
                    + "rabais1, "
                    + "rabais2, "
                    + "escompte, "
                    + "remarques) VALUES("
                    + "?, " //idEntreprise 1
                    + "?, " //titre 2
                    + "?, " //date 3
                    + "?, " //rabais1 4
                    + "?, " //rabais2 5
                    + "?, " //escompte 6
                    + "?)"); //remarques 7
            pstmt.setInt(1, lp_tmp.getIdEntreprise());
            pstmt.setString(2, lp_tmp.getTitre());
            pstmt.setInt(3, lp_tmp.getDate());
            pstmt.setInt(4, lp_tmp.getRabais1());
            pstmt.setInt(5, lp_tmp.getRabais2());
            pstmt.setInt(6, lp_tmp.getEscompte());
            pstmt.setString(7, lp_tmp.getRemarques());
            pstmt.executeUpdate();
            pstmt.close();              
            }
            catch(SQLException e){        
                Log.msg(1, "Fail add listePrix sql " + e);
            }        
        }
}
/*

*/



/*        try {
            tmpSql = File.createTempFile("soumManager-", ".tmp");
            tmpSql.deleteOnExit();
            Log.msg(0, tmpSql.getAbsolutePath());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - sqlProjet");
            alert.setHeaderText(null);
            alert.setContentText("Erreur : impossible de créer le fichier temporaire : " + ex.getMessage());
            alert.showAndWait(); 
        }        */