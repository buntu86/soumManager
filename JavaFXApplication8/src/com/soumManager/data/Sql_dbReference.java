package com.soumManager.data;

import com.soumManager.model.Catalog;
import com.soumManager.model.Position21;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Sql_dbReference {
    private static Connection conn = null;
    private static ObservableList<Position21> listReference = FXCollections.observableArrayList();    
    
    public static ObservableList<Position21> getReferenceByNumCat(int numCatalog) {
        connect();
        listReference.clear();
        String sql = "SELECT * FROM reference WHERE numCatalog = " + numCatalog + " ORDER BY numPos ASC";
        try{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Log.msg(0, "GetReferenceByNumCat : " + sql);
        while(rs.next()){
            listReference.add(new Position21(
                    rs.getInt("ID"),
                    rs.getInt("numCatalog"),
                    rs.getInt("yearCatalog"),
                    rs.getFloat("prix"),
                    rs.getString("text"),
                    rs.getInt("numPos"),
                    rs.getInt("idTypePrix")
            ));}
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }     
        return listReference;
    }
    
    private static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + Config.getPath_dbReference());
            Log.msg(0, "connexion");
        } catch (SQLException e) {
            Log.msg(1, "Fail to create file " + Config.getPath_dbReference() + " | "+ e.getMessage());
        } 
    }

    //ADD
    public static void addReference(String numPos, String text, String prix, String annee, int numCatalog) {
        if(!numPos.trim().isEmpty())
        {
            connect();
            String sql = "INSERT INTO reference (numCatalog, yearCatalog, prix, text, numPos, idTypePrix) VALUES (?,?,?,?,?,?)";
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                //Année
                annee = annee.replaceAll("[^\\d]", "");
                if(annee.trim().isEmpty())
                    annee = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
                
                //NumPos
                numPos = numPos.replaceAll("[^\\d]", "");
                if(numPos.isEmpty())
                    numPos = "0";             
                
                //Prix
                try {
                    Float.parseFloat(prix);
                }
                catch (NumberFormatException ex) {
                    prix = "0";
                }
                pstmt.setInt(1, numCatalog);
                pstmt.setInt(2, Integer.parseInt(annee));
                pstmt.setFloat(3, Float.parseFloat(prix));
                pstmt.setString(4, text);
                pstmt.setInt(5, Integer.parseInt(numPos));
                pstmt.setInt(6, 1);
                pstmt.executeUpdate();

                Log.msg(0, "Add row : " + sql);
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur - ajout d'une réference");
                alert.setHeaderText(null);
                alert.setContentText("Erreur SQL : " + e.getMessage());
                alert.showAndWait();   
            } 
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - ajout d'une réference");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de position est obligatoire.");
            alert.showAndWait();
        }
    }

    // UPDATE
    public static void updateReferenceNumPos(String numPos, int id) {
        if(id > 0){
            connect();
            String sql = "UPDATE reference SET numPos=? WHERE ID=?";
            try{
                numPos = numPos.replaceAll("[^\\d]", "");
                if(numPos.isEmpty())
                    numPos = "0";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(numPos));
                pstmt.setInt(2, id);
                pstmt.executeUpdate();

                Log.msg(0, "Update row : " + sql);
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur - modifier une réference");
                alert.setHeaderText(null);
                alert.setContentText("Erreur SQL : " + e.getMessage());
                alert.showAndWait();   
            }             
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - modifier une réference");
            alert.setHeaderText(null);
            alert.setContentText("Problème avec l'ID de la réference.");
            alert.showAndWait();        
        }
    }
    public static void updateReferenceText(String text, int id) {
        if(id > 0){
            connect();
            String sql = "UPDATE reference SET text=? WHERE ID=?";
            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, text);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();

                Log.msg(0, "Update row : " + sql);
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur - modifier une réference");
                alert.setHeaderText(null);
                alert.setContentText("Erreur SQL : " + e.getMessage());
                alert.showAndWait();   
            }             
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - modifier une réference");
            alert.setHeaderText(null);
            alert.setContentText("Problème avec l'ID de la réference.");
            alert.showAndWait();        
        }
    }   
    public static void updateReferencePrix(String prix, int id) {
        if(id > 0){
            connect();
            String sql = "UPDATE reference SET prix=? WHERE ID=?";
            try{
                try {
                    Float.parseFloat(prix);
                }
                catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur - modifier une réference");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur : la valeur de prix est incorrect.");
                    alert.showAndWait();   
                    return;
                }
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setFloat(1, Float.parseFloat(prix));
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
                Log.msg(0, "Update row : " + sql);
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur - modifier une réference");
                alert.setHeaderText(null);
                alert.setContentText("Erreur SQL : " + e.getMessage());
                alert.showAndWait();   
            }             
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - modifier une réference");
            alert.setHeaderText(null);
            alert.setContentText("Problème avec l'ID de la réference.");
            alert.showAndWait();        
        }
    }    
}