package com.soumManager.model;

import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;

public class Sia451_01s {
    private Projet projet;
    private String projetNum, projetName;
    private static List<String> listString = null;
    
    public Sia451_01s(Path fileSia451) {
        try {
            listString = Files.readAllLines(fileSia451, Charset.forName("IBM437"));

            //PROJET
            projet = new Projet(getNumMandat(), getNomMandat());
            
            //CAT CHAPTER
            setListCatChapter();
            
            //POSITION
            setPositions();
            
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - fichier sia451");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'ouverture du fichier sia451. Le fichier n'existe pas./n " + ex );
            alert.showAndWait(); 
        }
    }   

    public Projet getProjet(){
        return this.projet;
    }    
    
    private String getNumMandat(){
        String numMandat = new String();

        numMandat = listString.stream()
                .filter(line -> line.startsWith("A"))
                .findFirst()
                .get();

        numMandat=numMandat.substring(75, 84).replaceAll(" ", "");
        if(numMandat.isEmpty())
            numMandat = "---";

        return numMandat;
    }    
    
    public String getNomMandat(){
        String nomMandat = new String();
        
        nomMandat = listString.stream()
                .filter(line -> line.startsWith("A"))
                .findFirst()
                .get();

        nomMandat=nomMandat.substring(92, 122).trim();
        
        if(nomMandat.isEmpty())
            nomMandat = "inconnu";
        
        return nomMandat;
    }    
    private void setListCatChapter() {
        List<String> list = listString.stream()
                .filter(line -> line.startsWith("G") && line.substring(41,42).equals("1"))
                .collect(Collectors.toList());
        
        int i = 0;
        for(String element : list)
        {            
            int num = Integer.parseInt(element.substring(1, 4));
            //annee = Integer.parseInt(str.substring(5, 7));
            String titre = element.substring(92).trim().replaceAll("  +", "");            

            Catalog catChapter = new Catalog(i, num, titre, "0");
            projet.addCatChapter(catChapter);
            i++;
        }
    }   
    private void setPositions() {
        List<String> list = listString.stream()
                .filter(line -> line.startsWith("G") && line.substring(41,42).equals("2"))
                .collect(Collectors.toList());        
        
        int id = 0;
        
        for(String element : list)
        {
            int numCatalog=0, numPos=0;
            String text = "inconnu";
            
            if(element.length()>=4)
                numCatalog = Tools.stringToInt(element.substring(1, 4));
            
            if(element.length()>=13)
                numPos = Tools.stringToInt(element.substring(7, 13));
            
            if(element.length()>=92)
                text = element.substring(92);           
            
            //Log.msg(0, "CAN " + numCatalog + "|numPos " + numPos + "|text " + text);

            projet.addPosition(new Position(id, 
                    numCatalog, //numCatalog
                    0, //yearCatalog
                    0, //prix
                    text, //texte
                    numPos, //numPos
                    1));
            id++;
        }    
    }
}
