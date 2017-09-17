package com.soumManager.data;

import com.soumManager.model.Catalog;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql_catalog {
    private static Connection conn=null;
    
    public static void ini() {
        connect();
        String sql = "SELECT * FROM catalogs ORDER BY num ASC";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Log.msg(0, "Sql_listMandat | ResultSet sql: " + sql);
            while(rs.next()){
                Config.getListCatalog().add(new Catalog(rs.getInt("ID"), rs.getInt("num"), rs.getString("nom"), ""));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }        
    }
    private static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + Config.getPath_listCatalog());
            Log.msg(0, "Sql_listCatalog | connexion");
        } catch (SQLException e) {
            Log.msg(1, "Sql_listCatalog | Fail to create file " + Config.getPath_listCatalog() + " | "+ e.getMessage());
        } 
    }
}